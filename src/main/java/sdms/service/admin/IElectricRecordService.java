package sdms.service.admin;

import sdms.dto.admin.ElectricRecordDto;
import sdms.entity.admin.ElectricRecord;
import sdms.util.Page;

/**
 * Created by cheng on 2017/2/1.
 */
public interface IElectricRecordService {

    ElectricRecord findElectricRecordById(Integer id);

    boolean deleteElectricRecord(Integer[] id);

    boolean addElectricRecord(ElectricRecord electricRecord);

    Page<ElectricRecordDto> pageElectricRecord(Integer currentPage, Integer pageSize,
                                               Integer buildingId, Integer dormitoryId,
                                               String startTime, String endTime, Integer pay);

    boolean updateElectricRecord(ElectricRecord electricRecord);

    boolean updatePay(Integer id);
}
