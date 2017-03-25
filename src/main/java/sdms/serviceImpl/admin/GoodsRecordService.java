package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.GoodsRecordDao;
import sdms.dto.admin.GoodsRecordDto;
import sdms.entity.admin.GoodsRecord;
import sdms.service.admin.IGoodsRecordService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/2/2.
 */
@Service
public class GoodsRecordService implements IGoodsRecordService {

    @Autowired
    private GoodsRecordDao goodsRecordDao;

    public GoodsRecord findGoodsRecordById(Integer id){
        if(id == null){
            return null;
        }
        else{
            String hql = "select a from GoodsRecord a where a.id = ? ";
            Object[] param = new Object[1];
            param[0] = id;
            List<GoodsRecord> list = goodsRecordDao.find(hql, param);
            if(null == list || list.size() ==0){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    public boolean addGoodsRecord(GoodsRecord goodsRecord){
        if( null == goodsRecord){
            return false;
        }
        else{
            goodsRecord.setStatus(1);
            goodsRecordDao.save(goodsRecord);
            return true;
        }
    }

    public boolean deleteGoodsRecord(Integer[] id){
        if( null == id || id.length == 0){
            return false;
        }
        else{
            GoodsRecord goodsRecord;
            for(int i = 0; i < id.length; i++){
                goodsRecord = findGoodsRecordById(id[i]);
                if(goodsRecord == null) return false;
                goodsRecord.setStatus(0);
                goodsRecordDao.update(goodsRecord);
            }
            return true;
        }
    }

    public Page<GoodsRecordDto> pageGoodsRecord(Integer currentPage, Integer pageSize,
                                                Integer buildingId, String name,
                                                String startTime, String endTime){
        StringBuilder hql = new StringBuilder();
        hql.append("select g.id as id, g.goods as goods, g.leave_time as leaveTime, g.building_id as buildingId," +
                "g.name as name, g.status as status, b.number as buildingNumber " +
                "from building b join goods_record g on b.id = g.building_id " +
                "where g.status = 1");
        StringBuilder countHql = new StringBuilder();
        countHql.append("select count(*) " +
                "from building b join goods_record g on b.id = g.building_id " +
                "where g.status = 1");
        Object[] param = new Object[4];
        if(null != buildingId && buildingId != 0){
            hql.append(" and g.building_id = ?");
            countHql.append(" and  g.building_id = ?");
            param[0] = buildingId;
        }
        if(null != name && !name.equals("")){
            hql.append(" and g.name like ? ");
            countHql.append(" and g.name like ?");
            param[1] = "%" + name + "%";
        }
        if(null != startTime && !startTime.equals("")){
            hql.append(" and g.leave_time >= ? ");
            countHql.append(" and  g.leave_time >= ? ");
            param[2] = startTime;
        }
        if(null != endTime && !endTime.equals("")){
            hql.append(" and g.leave_time <= ?");
            countHql.append(" and g.leave_time <= ?");
            param[3] = endTime;
        }

        List<GoodsRecordDto> list = goodsRecordDao.pageSQL(hql.toString(), param, currentPage, pageSize, GoodsRecordDto.class);
        Long count =goodsRecordDao.countSQL(countHql.toString(), param);
        Page<GoodsRecordDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setResults(list);
        page.setTotal(count);

        return page;

    }

    public boolean updateGoodsRecord(GoodsRecord goodsRecord){
        if( null == goodsRecord){
            return false;
        }
        else{
            GoodsRecord oldGoodsRecord = findGoodsRecordById(goodsRecord.getId());
            oldGoodsRecord.setLeaveTime(goodsRecord.getLeaveTime());
            oldGoodsRecord.setGoods(goodsRecord.getGoods());
            oldGoodsRecord.setName(goodsRecord.getName());
            goodsRecordDao.update(oldGoodsRecord);
            return true;
        }
    }



}
