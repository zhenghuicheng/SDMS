package sdms.service.admin;

import sdms.dto.admin.DormitoryDto;
import sdms.dto.admin.DormitoryStatisticsDto;
import sdms.entity.admin.Dormitory;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/30.
 */
public interface IDormitoryService {

    Dormitory findDormitoryById(Integer id);

    boolean addDormitory(Dormitory dormitory);

    Page<DormitoryDto> pageDormitory(Integer currentPage, Integer pageSize, Integer buildingId, Integer floor, Integer dormitoryId,
                                     String type, Integer studentNumber);

    boolean addAllDormitory(Integer buildingId,  Integer buildingNumber, Integer floor, Integer floorNumber, Integer bedNumber);

    List<Dormitory> listDormitoryByBuildingId(Integer buildingId);

    boolean updateDormitory(Dormitory dormitory);

    boolean deleteDormitory(Integer[] id);

    Dormitory findDormitoryByNumber(Integer building, Integer number);

    Long countDormitoryByBuildingId(Integer buildingId);

    Long countDormitoryByBuildingidAndNumber(Integer buildingId, Integer number);

    Long countDormitoryRemainBedNumber(Integer buildingId);

    List<Dormitory> listDormitoryByBuildingIdAndFloor(Integer buildingId, Integer floor);

    Page<DormitoryStatisticsDto> statisticsDormitory(Integer currentPage, Integer pageSize, Integer buildingId, String type, Integer studentNumber);


}
