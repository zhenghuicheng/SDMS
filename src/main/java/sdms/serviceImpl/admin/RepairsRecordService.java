package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.utils.DateUtil;
import sdms.dao.admin.RepairsRecordDao;
import sdms.dto.admin.RepairsRecordDto;
import sdms.entity.admin.RepairsRecord;
import sdms.entity.admin.Student;
import sdms.service.admin.IRepairsRecordService;
import sdms.service.admin.IStudentService;
import sdms.util.Page;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cheng on 2017/2/4.
 */
@Service
public class RepairsRecordService implements IRepairsRecordService{

    @Autowired
    private RepairsRecordDao repairsRecordDao;

    @Autowired
    private IStudentService studentService;

    public RepairsRecord findRepairsRecordById(Integer id){
        if( null == id){
            return null;
        }
        else{
            String hql = "select a from RepairsRecord a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<RepairsRecord> list = repairsRecordDao.find(hql, param);
            if(null == list || list.size() == 0){
                return null;
            }
            else {
                return list.get(0);
            }
        }
    }

    public boolean addRepairsRecord(RepairsRecord repairsRecord, Integer studentId){
        Student student = studentService.findStudentById(studentId);
        if( null == student){
            return false;
        }
        else{
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String number = df.format(date) + student.getNumber();
            repairsRecord.setNumber(number);
            repairsRecord.setDormitoryId(student.getDormitoryId());
            repairsRecord.setRepairsTime(DateUtil.getSimpleDate(new Date()));
            repairsRecord.setName(student.getName());
            repairsRecord.setStatus(1);
            repairsRecord.setIsRepairs(0);
            repairsRecord.setStudentId(student.getId());
            repairsRecordDao.save(repairsRecord);
            return true;
        }
    }

    public boolean deleteRepairsRecord(Integer[] id){
        if( null == id || id.length == 0){
            return false;
        }
        else{
            RepairsRecord repairsRecord;
            for( int i = 0; i < id.length; i++){
                repairsRecord = findRepairsRecordById(id[i]);
                repairsRecord.setStatus(0);
                repairsRecordDao.update(repairsRecord);
            }
            return true;
        }
    }

    public Page<RepairsRecordDto> pageRepairRecord(Integer currentPage, Integer pageSize,
                                                   Integer buildingId, Integer dormitoryId,
                                                   String startTime, String endTime,
                                                   String name, Integer isRepairs,
                                                   Integer studentId, String number){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        hql.append("select r.id as id, r.dormitory_id as dormitoryId, r.student_id as studentId, r.repairs_time as repairsTime, " +
                "r.is_repairs as isRepairs, r.context as context, r.name as name, d.building_number as buildingNumber, d.number as dormitoryNumber, r.remark as remark, r.number as number " +
                " from repairs_record r join dormitory d on r.dormitory_id = d.id where r.status = 1 ");
        countHql.append("select count(*) " +
                " from repairs_record r join dormitory d on r.dormitory_id = d.id where r.status = 1 ");
        Object[] param = new Object[8];

        if( null != buildingId && 0 != buildingId){
            hql.append(" and d.building_id = ? ");
            countHql.append(" and d.building_id = ? ");
            param[0] = buildingId;
        }
        if( null != dormitoryId && 0 != dormitoryId){
            hql.append(" and d.id = ? ");
            countHql.append(" and d.id = ? ");
            param[1] = dormitoryId;
        }
        if( null != startTime && !startTime.equals("")){
            hql.append(" and r.repairs_time >= ? ");
            countHql.append(" and r.repairs_time >= ? ");
            param[2] = startTime;
        }
        if( null != endTime && !endTime.equals("")){
            hql.append(" and r.repairs_time <= ? ");
            countHql.append(" and  r.repairs_time <= ? ");
            param[3] = endTime;
        }
        if( null != name && !name.equals("")){
            hql.append(" and r.name like ? ");
            countHql.append(" and r.name like ? ");
            param[4] = "%" + name + "%";
        }
        if( null != isRepairs && isRepairs != -1){
            hql.append(" and r.is_repairs = ? ");
            countHql.append(" and r.is_repairs = ? ");
            param[5] = isRepairs;
        }
        if( null != studentId && 0 != studentId){
            hql.append(" and  r.student_id =  ? ");
            countHql.append(" and r.student_id = ? ");
            param[6] = studentId;
        }
        if (null != number && !number.equals("")){
            hql.append(" and r.number like ? ");
            countHql.append(" and r.number like ? ");
            param[7] = "%" + number + "%";
        }
        hql.append(" order by r.repairs_time desc");

        List<RepairsRecordDto> list = repairsRecordDao.pageSQL(hql.toString(), param, currentPage, pageSize, RepairsRecordDto.class);
        Long count = repairsRecordDao.countSQL(countHql.toString(), param);
        Page<RepairsRecordDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setResults(list);
        page.setTotal(count);

        return page;

    }

    public boolean updateIsRepairs(Integer id, Integer state){
        if( null == id){
            return false;
        }
        else{
            RepairsRecord repairsRecord = findRepairsRecordById(id);
            if( null == repairsRecord){
                return false;
            }
            else{
                if( repairsRecord.getIsRepairs() == 2){
                    return false;
                }
                else{
                    repairsRecord.setIsRepairs(state);
                    repairsRecordDao.update(repairsRecord);
                    return true;
                }

            }
        }
    }

    public boolean updateRepairsRecord(RepairsRecord repairsRecord){
        if(null == repairsRecord){
            return false;
        }
        else{
            RepairsRecord oldRepairsRecord = findRepairsRecordById(repairsRecord.getId());
            if(null == oldRepairsRecord || oldRepairsRecord.getIsRepairs() == 2){
                return false;
            }
            oldRepairsRecord.setContext(repairsRecord.getContext());
            oldRepairsRecord.setRemark(repairsRecord.getRemark());
            repairsRecordDao.update(oldRepairsRecord);
            return true;
        }
    }
}
