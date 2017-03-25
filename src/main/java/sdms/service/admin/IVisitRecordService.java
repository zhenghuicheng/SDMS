package sdms.service.admin;

import sdms.dto.admin.VisitRecordDto;
import sdms.entity.admin.VisitRecord;
import sdms.util.Page;

/**
 * Created by cheng on 2017/2/3.
 */
public interface IVisitRecordService {

    VisitRecord findVisitRecordById(Integer id);

    boolean addVisitRecord(VisitRecord visitRecord);

    boolean deleteVisitRecord(Integer[] id);

    Page<VisitRecordDto> pageVisitRecord(Integer currentPage, Integer pageSize,
                                         Integer buildingId, Integer dormitoryId,
                                         String startTime, String endTime,
                                         String visitor, String visitedName);

    boolean updateVisitRecord(VisitRecord visitRecord);
}
