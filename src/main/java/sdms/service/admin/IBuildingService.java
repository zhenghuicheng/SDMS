package sdms.service.admin;

import sdms.dto.admin.BuildingDto;
import sdms.entity.admin.Building;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/30.
 */
public interface IBuildingService {

    Building findBuildingById(Integer id);

    boolean addBuilding(Building building);

    Page<BuildingDto> pageBuilding(Integer currentPage, Integer pageSize,
                                   Integer buildingId, Integer adminId);

    List<Building> listBuilding();

    boolean updateBuilding(Building building);

    boolean deleteBuilding(Integer[] id);

    Long countBuildingByAdminId(Integer adminId);

    Long countBuildingByNumber(Integer number);

    List<Building> listBuildingByProfessionAndSex(Integer department, Integer sex);
}
