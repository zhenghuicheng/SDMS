package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.VisitRecordDao;
import sdms.dto.admin.VisitRecordDto;
import sdms.entity.admin.VisitRecord;
import sdms.service.admin.IVisitRecordService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/2/3.
 */
@Service
public class VisitRecordService implements IVisitRecordService{

    @Autowired
    private VisitRecordDao visitRecordDao;

    public VisitRecord findVisitRecordById(Integer id){
        if( null == id){
            return null;
        }
        else{
            String hql = "select a from VisitRecord a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<VisitRecord> list = visitRecordDao.find(hql, param);
            if( null == list || list.size() == 0){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    public boolean addVisitRecord(VisitRecord visitRecord){
        if(null == visitRecord ){
            return false;
        }
        else{
            visitRecord.setStatus(1);
            visitRecordDao.save(visitRecord);
            return true;
        }
    }

    public boolean deleteVisitRecord(Integer[] id){
        if(null == id || id.length == 0){
            return false;
        }
        else{
            VisitRecord visitRecord;
            for( int i = 0; i < id.length; i++){
                visitRecord = findVisitRecordById(id[i]);
                visitRecord.setStatus(0);
                visitRecordDao.update(visitRecord);
            }
        }

        return true;
    }

    public boolean updateVisitRecord(VisitRecord visitRecord){
        if( null == visitRecord){
            return false;
        }
        else{
            VisitRecord oldVisitRecord = findVisitRecordById(visitRecord.getId());
            oldVisitRecord.setStartTime(visitRecord.getStartTime());
            oldVisitRecord.setEndTime(visitRecord.getEndTime());
            oldVisitRecord.setVisitedName(visitRecord.getVisitedName());
            oldVisitRecord.setVisitor(visitRecord.getVisitor());
            oldVisitRecord.setVisitorNumber(visitRecord.getVisitorNumber());
            visitRecordDao.update(oldVisitRecord);
            return true;
        }
    }

    public Page<VisitRecordDto> pageVisitRecord(Integer currentPage, Integer pageSize,
                                                Integer buildingId, Integer dormitoryId,
                                                String startTime, String endTime,
                                                String visitor, String visitedName){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        hql.append("select  v.id as id, v.dormitory_id as dormitoryId, v.visitor as visitor, " +
                "v.visitor_number as visitorNumber, v.visited_name as visitedName, v.start_time as startTime, " +
                "v.end_time as endTime, v.status as status, d.building_number as buildingNumber, d.number as dormitoryNumber " +
                "from dormitory d join visit_record v on d.id = v.dormitory_id " +
                "where v.status = 1");
        countHql.append("select  count(*) " +
                "from dormitory d join visit_record v on d.id = v.dormitory_id " +
                "where v.status = 1");
        Object[] param = new Object[6];

        if(null != buildingId && 0 != buildingId){
            hql.append(" and d.building_id = ?");
            countHql.append(" and d.building_id = ?");
            param[0] = buildingId;
        }
        if(null != dormitoryId && 0 != dormitoryId){
            hql.append(" and d.id = ?");
            countHql.append(" and d.id = ?");
            param[1] = dormitoryId;
        }
        if(null != startTime && !startTime.equals("")){
            hql.append(" and v.start_time >= ? ");
            countHql.append(" and v.start_time >= ?");
            param[2] = startTime;
        }
        if(null != endTime && !endTime.equals("")){
            hql.append(" and v.start_time <= ? ");
            countHql.append(" and v.start_time <= ?");
            param[3] = endTime;
        }
        if(null != visitor && !visitor.equals("")){
            hql.append(" and v.visitor like ? ");
            countHql.append(" and v.visitor like ? ");
            param[4] = "%" + visitor + "%";
        }
        if(null != visitedName && !visitedName.equals("")){
            hql.append(" and v.visited_name like ? ");
            countHql.append(" and v.visited_name like ? ");
            param[5] = "%" + visitedName + "%";
        }


        List<VisitRecordDto> list = visitRecordDao.pageSQL(hql.toString(), param, currentPage, pageSize, VisitRecordDto.class);
        Long count = visitRecordDao.countSQL(countHql.toString(), param);
        Page<VisitRecordDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setResults(list);
        page.setTotal(count);

        return page;

    }


}
