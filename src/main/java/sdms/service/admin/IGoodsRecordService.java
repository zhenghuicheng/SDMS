package sdms.service.admin;

import sdms.dto.admin.GoodsRecordDto;
import sdms.entity.admin.GoodsRecord;
import sdms.util.Page;

/**
 * Created by cheng on 2017/2/2.
 */
public interface IGoodsRecordService {

    GoodsRecord findGoodsRecordById(Integer id);

    boolean addGoodsRecord(GoodsRecord goodsRecord);

    boolean deleteGoodsRecord(Integer[] id);

    Page<GoodsRecordDto> pageGoodsRecord(Integer currentPage, Integer pageSize,
                                         Integer buildingId, String name,
                                         String startTime, String endTime);

    boolean updateGoodsRecord(GoodsRecord goodsRecord);
}
