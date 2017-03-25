package sdms.service.admin;

import sdms.dto.admin.WaterRecordDto;
import sdms.entity.admin.WaterRecord;
import sdms.util.Page;

/**
 * Created by cheng on 2017/1/31.
 */
public interface IWaterRecordService {

    WaterRecord findWaterRecordById(Integer id);

    boolean addWaterRecord(WaterRecord waterRecord);

    boolean deleteWaterRecord(Integer[] id);

    Page<WaterRecordDto> pageWaterRecord(Integer currentPage,
                                         Integer pageSize, Integer buildingId,
                                         Integer dormitoryId, String startTime, String endTime, Integer pay);

    boolean updateWaterRecord(WaterRecord waterRecord);

    boolean updateWaterRecordPay(Integer id);
}
