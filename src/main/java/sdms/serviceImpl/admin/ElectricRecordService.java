package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.ElectricRecordDao;
import sdms.dto.admin.ElectricRecordDto;
import sdms.entity.admin.ElectricRecord;
import sdms.service.admin.IElectricRecordService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/2/1.
 */
@Service
public class ElectricRecordService implements IElectricRecordService {

    @Autowired
    private ElectricRecordDao electricRecordDao;

    public ElectricRecord findElectricRecordById(Integer id){
        if( null == id){
            return null;
        }
        else{
            String hql = "select a from ElectricRecord a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<ElectricRecord> list = electricRecordDao.find(hql, param);
            if( null == list || list.size() ==0){
                return null;
            }
            else{
                return list.get(0);
            }

        }
    }

    public boolean addElectricRecord(ElectricRecord electricRecord){
        if( null == electricRecord){
            return false;
        }
        else{
            electricRecord.setPay(0);
            electricRecord.setStatus(1);
            electricRecordDao.save(electricRecord);
            return true;
        }
    }

    public boolean deleteElectricRecord(Integer[] id){
        if(null == id || id.length == 0 ){
            return false;
        }
        else{
            ElectricRecord electricRecord;
            for(int i = 0; i < id.length; i ++){
                electricRecord = findElectricRecordById(id[i]);
                electricRecord.setStatus(0);
                electricRecordDao.update(electricRecord);
            }
            return true;
        }
    }

    public Page<ElectricRecordDto> pageElectricRecord(Integer currentPage, Integer pageSize,
                                                Integer buildingId, Integer dormitoryId,
                                                String startTime, String endTime, Integer pay){
        StringBuilder hql = new StringBuilder();
        hql.append("select w.id as id, w.dormitory_id as dormitoryId, w.status as status, " +
                "w.start_time as startTime, w.end_time as endTime, w.electric_rate as electricRate, w.pay as pay, " +
                "d.building_number as buildingNumber, d.number as dormitoryNumber " +
                "from dormitory d join electric_record w on d.id = w.dormitory_id " +
                "where w.status = 1");
        StringBuilder countHql = new StringBuilder();
        countHql.append("select count(*) " +
                "from dormitory d join electric_record w on d.id = w.dormitory_id " +
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
        if (null != pay && pay != 0) {
            hql.append(" and w.pay = ? ");
            countHql.append(" and w.pay = ? ");
            if (pay == 1){
                param[4] = 1;
            }
            else{
                param[4] = 0;
            }
        }

        List<ElectricRecordDto> list = electricRecordDao.pageSQL(hql.toString(), param, currentPage, pageSize, ElectricRecordDto.class);
        Long count = electricRecordDao.countSQL(countHql.toString(), param);
        Page<ElectricRecordDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setTotal(count);
        page.setResults(list);
        return page;
    }

    public boolean updateElectricRecord(ElectricRecord electricRecord){
        if(null == electricRecord){
            return false;
        }
        else{
            ElectricRecord oldElectricRecord = findElectricRecordById(electricRecord.getId());
            oldElectricRecord.setStartTime(electricRecord.getStartTime());
            oldElectricRecord.setEndTime(electricRecord.getEndTime());
            oldElectricRecord.setElectricRate(electricRecord.getElectricRate());
            electricRecordDao.update(oldElectricRecord);
            return true;
        }
    }

    public boolean updatePay(Integer id){
        if( null == id){
            return false;
        }
        else{
            ElectricRecord electricRecord = findElectricRecordById(id);
            if(null == electricRecord){
                return false;
            }
            else{
                electricRecord.setPay(1);
                electricRecordDao.update(electricRecord);
                return true;
            }
        }

    }
}
