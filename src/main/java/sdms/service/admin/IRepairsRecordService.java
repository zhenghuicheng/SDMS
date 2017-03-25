package sdms.service.admin;

import sdms.dto.admin.RepairsRecordDto;
import sdms.entity.admin.RepairsRecord;
import sdms.util.Page;

/**
 * Created by cheng on 2017/2/4.
 */
public interface IRepairsRecordService {

    RepairsRecord findRepairsRecordById(Integer id);

    boolean addRepairsRecord(RepairsRecord repairsRecord, Integer studentId);

    boolean deleteRepairsRecord(Integer[] id);

    Page<RepairsRecordDto> pageRepairRecord(Integer currentPage, Integer pageSize,
                                            Integer buildingId, Integer dormitoryId,
                                            String startTime, String endTime,
                                            String name, Integer isRepairs,
                                            Integer studentId, String number);

    boolean updateIsRepairs(Integer id, Integer state);

    boolean updateRepairsRecord(RepairsRecord repairsRecord);

    }
