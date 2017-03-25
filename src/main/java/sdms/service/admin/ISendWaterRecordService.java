package sdms.service.admin;

import sdms.dto.admin.SendWaterRecordDto;
import sdms.entity.admin.SendWaterRecord;
import sdms.util.Page;

/**
 * Created by cheng on 2017/2/5.
 */
public interface ISendWaterRecordService {

    SendWaterRecord findSendWaterRecordById(Integer id);

    boolean addSendWaterRecord(SendWaterRecord sendWaterRecord, Integer studentId);

    boolean deleteSendWaterRecord(Integer[] id);

    boolean updateIsSend(Integer id);

    Page<SendWaterRecordDto> pageSendWaterRecord(Integer currentPage, Integer pageSize,
                                                 Integer buildingId, Integer dormitoryId,
                                                 String startTime, String endTime,
                                                 String name, Integer isSend,
                                                 Integer studentId);

    boolean updateSendWaterRecord(SendWaterRecord sendWaterRecord);
}
