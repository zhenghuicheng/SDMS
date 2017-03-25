package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.utils.DateUtil;
import sdms.dao.admin.SendWaterRecordDao;
import sdms.dto.admin.SendWaterRecordDto;
import sdms.entity.admin.SendWaterRecord;
import sdms.entity.admin.Student;
import sdms.service.admin.ISendWaterRecordService;
import sdms.service.admin.IStudentService;
import sdms.util.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by cheng on 2017/2/5.
 */
@Service
public class SendWaterRecordService implements ISendWaterRecordService {

    @Autowired
    private SendWaterRecordDao sendWaterRecordDao;

    @Autowired
    private IStudentService studentService;

    public SendWaterRecord findSendWaterRecordById(Integer id){
        if( null == id){
            return null;
        }
        else{
            String hql = "select a from SendWaterRecord a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<SendWaterRecord> list = sendWaterRecordDao.find(hql, param);
            if( null == list || list.size() == 0){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    public boolean addSendWaterRecord(SendWaterRecord sendWaterRecord, Integer studentId){
        if( null == sendWaterRecord){
            return false;
        }
        else{
            Student student = studentService.findStudentById(studentId);
            if( null == student){
                return false;
            }
            sendWaterRecord.setStatus(1);
            sendWaterRecord.setDormitoryId(student.getDormitoryId());
            sendWaterRecord.setIsSend(0);
            sendWaterRecord.setStudentId(studentId);
            sendWaterRecord.setTime(DateUtil.getSimpleDate(new Date()));
            sendWaterRecord.setName(student.getName());
            sendWaterRecordDao.save(sendWaterRecord);

            return true;
        }
    }

    public boolean deleteSendWaterRecord(Integer[] id){
        if( null == id || id.length == 0){
            return false;
        }
        else{
            SendWaterRecord sendWaterRecord;
            for(int i = 0; i < id.length; i++){
                sendWaterRecord = findSendWaterRecordById(id[i]);
                if( null == sendWaterRecord){
                    return false;
                }
                sendWaterRecord.setStatus(0);
                sendWaterRecordDao.save(sendWaterRecord);
            }
            return true;
        }
    }

    public boolean updateIsSend(Integer id){
        if( null == id){
            return false;
        }
        else{
            SendWaterRecord sendWaterRecord = findSendWaterRecordById(id);
            if( null == sendWaterRecord){
                return false;
            }
            else{
                sendWaterRecord.setIsSend(1);
                sendWaterRecordDao.update(sendWaterRecord);
                return true;
            }
        }


    }

    public Page<SendWaterRecordDto> pageSendWaterRecord(Integer currentPage, Integer pageSize,
                                                        Integer buildingId, Integer dormitoryId,
                                                        String startTime, String endTime,
                                                        String name, Integer isSend,
                                                        Integer studentId){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        hql.append("select s.id as id, s.dormitory_id as  dormitoryId, s.student_id as studentId, " +
                "s.status as status, s.time as time, s.is_send as isSend, s.name as name, s.number as number, " +
                "d.building_number as buildingNumber, d.number as dormitoryNumber " +
                "from send_water_record s join dormitory d on s.dormitory_id = d.id where s.status = 1 ");
        countHql.append("select count(*) " +
                "from send_water_record s join dormitory d on s.dormitory_id = d.id where s.status = 1 ");
        Object[] param = new Object[7];

        if( null != buildingId && 0 != buildingId){
            hql.append(" and d.building_id = ? ");
            countHql.append(" and d.building_id = ? ");
            param[0] = buildingId;
        }
        if( null != dormitoryId && 0 != dormitoryId){
            hql.append(" and s.dormitory_id = ? ");
            countHql.append(" and s.dormitory_id = ? ");
            param[1] = dormitoryId;
        }
        if( null != startTime && !startTime.equals("")){
            hql.append(" and s.time >= ? ");
            countHql.append(" and s.time >= ? ");
            param[2] = startTime;
        }
        if( null != endTime && !endTime.equals("")){
            hql.append(" and s.time <= ? ");
            countHql.append(" and s.time <= ?");
            param[3] = endTime;
        }
        if( null != name && !name.equals("")){
            hql.append(" and s.name like ?");
            countHql.append(" and s.name like ? ");
            param[4] = "%" + name + "%";
        }
        if( null != isSend && isSend != -1){
            hql.append(" and s.is_send = ? ");
            countHql.append(" and  s.is_send = ? ");
            param[5] = isSend;
        }
        if( null != studentId && 0 != studentId){
            hql.append(" and  s.student_id = ? ");
            countHql.append(" and s.student_id = ? ");
            param[6] = studentId;
        }
        hql.append(" order by s.time desc");
        List<SendWaterRecordDto> list = sendWaterRecordDao.pageSQL(hql.toString(), param, currentPage, pageSize, SendWaterRecordDto.class);
        Long count = sendWaterRecordDao.countSQL(countHql.toString(), param);
        Page<SendWaterRecordDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setResults(list);
        page.setTotal(count);

        return page;
    }

    public boolean updateSendWaterRecord(SendWaterRecord sendWaterRecord){
        if(null == sendWaterRecord){
            return false;
        }
        else{
            SendWaterRecord oldSendWaterRecord = findSendWaterRecordById(sendWaterRecord.getId());
            if(null == oldSendWaterRecord){
                return false;
            }
            else{
                if(oldSendWaterRecord.getIsSend() == 1){
                    return false;
                }
                else{
                    oldSendWaterRecord.setNumber(sendWaterRecord.getNumber());
                    sendWaterRecordDao.update(oldSendWaterRecord);
                    return true;
                }

            }
        }
    }


}
