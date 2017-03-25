package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.WaterRecordDao;
import sdms.dto.admin.WaterRecordDto;
import sdms.entity.admin.WaterRecord;
import sdms.service.admin.IWaterRecordService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/31.
 */
@Service
public class WaterRecordService implements IWaterRecordService {

    @Autowired
    private WaterRecordDao waterRecordDao;

    public WaterRecord findWaterRecordById(Integer id){
        if( null == id){
            return null;
        }
        else{
            String hql = "select a from WaterRecord a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<WaterRecord> list = waterRecordDao.find(hql, param);
            if( null == list || list.size() ==0){
                return null;
            }
            else{
                return list.get(0);
            }

        }
    }

    public boolean addWaterRecord(WaterRecord waterRecord){
        if( null == waterRecord){
            return false;
        }
        else{
            waterRecord.setPay(0);
            waterRecord.setStatus(1);
            waterRecordDao.save(waterRecord);
            return true;
        }
    }

    public boolean deleteWaterRecord(Integer[] id){
        if(null == id || id.length == 0 ){
            return false;
        }
        else{
            WaterRecord waterRecord;
            for(int i = 0; i < id.length; i ++){
                 waterRecord = findWaterRecordById(id[i]);
                waterRecord.setStatus(0);
                waterRecordDao.update(waterRecord);
            }
            return true;
        }
    }

    public Page<WaterRecordDto> pageWaterRecord(Integer currentPage, Integer pageSize,
                                                Integer buildingId, Integer dormitoryId,
                                                String startTime, String endTime, Integer pay){
        StringBuilder hql = new StringBuilder();
        hql.append("select w.id as id, w.dormitory_id as dormitoryId, w.status as status, " +
                "w.start_time as startTime, w.end_time as endTime, w.water_rate as waterRate, w.pay as pay, " +
                "d.building_number as buildingNumber, d.number as dormitoryNumber " +
                "from dormitory d join water_record w on d.id = w.dormitory_id " +
                "where w.status = 1");
        StringBuilder countHql = new StringBuilder();
        countHql.append("select count(*) " +
                "from dormitory d join water_record w on d.id = w.dormitory_id " +
                "where w.status = 1");
        /*String hql = "select w.id as id, w.dormitory_id as dormitoryId, w.status as status, " +
                "w.start_time as startTime, w.end_time as endTime, w.water_rate as waterRate, w.pay as pay, " +
                "d.building_number as buildingNumber, d.number as dormitoryNumber " +
                "from dormitory d join water_record w on d.id = w.dormitory_id " +
                "where w.status = 1";*/
        /*String countHql = "select count(*) " +
                "from dormitory d join water_record w on d.id = w.dormitory_id " +
                "where w.status = 1";*/
        Object[] param = new Object[5];
        if(null != buildingId && buildingId != 0){
            hql.append(" and d.building_id = ?");
            countHql.append(" and d.building_id = ?");
            param[0] = buildingId;
        }
        if(null != dormitoryId && dormitoryId != 0){
            hql.append(" and w.dormitory_id = ?");
            countHql.append(" and w.dormitory_id = ?");
            param[1] = dormitoryId;
        }
        if(null != startTime && !startTime.equals("")){
            hql.append(" and w.start_time >= ?");
            countHql.append(" and w.start_time >= ?");
            param[2] = startTime;
        }
        if(null != endTime && !endTime.equals("")){
            hql.append(" and w.end_time <= ?");
            countHql.append(" and w.end_time <= ?");
            param[3] = endTime;
        }
        if(null != pay && pay != 0){
            hql.append(" and w.pay = ? ");
            countHql.append(" and w.pay = ? ");
            if(pay == 1){
                param[4] = 1;
            }
            else{
                param[4] = 0;
            }
        }

        List<WaterRecordDto> list = waterRecordDao.pageSQL(hql.toString(), param, currentPage, pageSize, WaterRecordDto.class);
        Long count = waterRecordDao.countSQL(countHql.toString(), param);
        Page<WaterRecordDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setTotal(count);
        page.setResults(list);
        return page;
    }

    public boolean updateWaterRecord(WaterRecord waterRecord){
        if(null == waterRecord){
            return false;
        }
        else{
            WaterRecord oldWaterRecord = findWaterRecordById(waterRecord.getId());
            oldWaterRecord.setStartTime(waterRecord.getStartTime());
            oldWaterRecord.setEndTime(waterRecord.getEndTime());
            oldWaterRecord.setWaterRate(waterRecord.getWaterRate());
            oldWaterRecord.setPay(1);
            waterRecordDao.update(oldWaterRecord);
            return true;
        }
    }

    public boolean updateWaterRecordPay(Integer id){
        if( null == id){
            return false;
        }
        else{
            WaterRecord waterRecord = findWaterRecordById(id);
            if( null == waterRecord){
                return false;
            }
            else{
                waterRecord.setPay(1);
                waterRecordDao.update(waterRecord);
                return true;
            }
        }
    }


}
