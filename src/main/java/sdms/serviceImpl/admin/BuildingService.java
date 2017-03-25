package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.BuildingDao;
import sdms.dto.admin.BuildingDto;
import sdms.entity.admin.Building;
import sdms.service.admin.IBuildingService;
import sdms.service.admin.IDormitoryService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/30.
 */
@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private IDormitoryService dormitoryService;



    public Building findBuildingById(Integer id){
        if( null == id ){
            return null;
        }
        else{
            String hql = "select a from Building a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<Building> list = buildingDao.find(hql, param);
            if( null == list || list.size() == 0){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    public boolean addBuilding(Building building){
        if( null == building){
            return false;
        }
        else{
            building.setStatus(1);
            buildingDao.save(building);
            dormitoryService.addAllDormitory(building.getId(), building.getNumber(), building.getFloor(), building.getFloorNumber(), building.getBedNumber());
            return true;
        }
    }

    public Page<BuildingDto> pageBuilding(Integer currentPage, Integer pageSize, Integer departmentId, Integer adminId){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        Boolean first = true;
        Object[] param = new Object[2];

        hql.append("select b.id as id, b.number as number, b.sex as sex, b.floor_number as floorNumber, b.floor as floor, b.bed_number as bedNumber,  " +
                "b.department_id as departmentId, d.name as departmentName, b.admin_id as adminId, b.adminName " +
                "from (select c.*, a.name as adminName, a.id as adminId from building c join admin a on c.admin_id = a.id " +
                "where c.status = 1) b " +
                " join department d on b.department_id = d.id ");
        countHql.append("select count(*) " +
                "from (select c.*, a.name as adminName, a.id as adminId from building c join admin a on c.admin_id = a.id " +
                "where c.status = 1) b " +
                " join department d on b.department_id = d.id ");
        if(null != departmentId && departmentId != 0){
            if(first == true){
                hql.append(" where b.department_id = ? ");
                countHql.append(" where b.department_id = ?");
                param[0] = departmentId;
            }
            else{
                hql.append(" and b.department_id = ? ");
                countHql.append(" and b.department_id = ? ");
                param[0] = departmentId;
            }
            first = false;
        }
        if(null != adminId && adminId != 0){
            if(first == true){
                hql.append(" where b.adminId = ? ");
                countHql.append("where b.adminId = ? ");
                param[1] = adminId;
            }
            else{
                hql.append(" and b.adminId = ? ");
                countHql.append(" and b.adminId = ? ");
                param[1] = adminId;
            }
           first = false;
        }
        hql.append(" order by b.number ");

        /*String hql = "select b.id as id, b.number as number, b.sex as sex, b.floor_number as floorNumber, b.floor as floor, b.bed_number as bedNumber,  " +
                "b.department_id as departmentId, d.name as departmentName, b.admin_id as adminId, b.adminName " +
                "from (select c.*, a.name as adminName  from buinlding c join admin a on c.admin_id = a.id " +
                "where c.status = 1) b " +
                " join department d on b.department_id = d.id ";

        String countHql = "select count(*) " +
                "from (select c.*, a.name as adminName  from building c join admin a on c.admin_id = a.id " +
                "where c.status = 1) b " +
                " join department d on b.department_id = d.id ";*/
        List<BuildingDto> list = buildingDao.pageSQL(hql.toString(), param, currentPage, pageSize, BuildingDto.class);
        //Long count = buildingDao.countSQL(countHql.toString(), param);
        Page<BuildingDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setTotal(10);
        page.setResults(list);

        return page;
    }

    public List<Building> listBuilding(){
        String hql = "select b from Building b where b.status = 1";
        Object[] param = new Object[0];
        List<Building> list= buildingDao.find(hql, param);
        return list;
    }

    public boolean updateBuilding(Building building){
        if(null == building){
            return false;
        }
        else{
            Building oldBuilding = findBuildingById(building.getId());
            if(null == oldBuilding){
                return false;
            }
            else{
                oldBuilding.setAdminId(building.getAdminId());
                oldBuilding.setDepartmentId(building.getDepartmentId());
                oldBuilding.setSex(building.getSex());
                buildingDao.update(oldBuilding);
                return true;
            }
        }
    }

    public boolean deleteBuilding(Integer[] id){
        if(null == id || id.length == 0){
            return false;
        }
        else{
            Building building;
            for(int i = 0; i < id.length; i++){
                building = findBuildingById(id[i]);
                if(null == building){
                    return false;
                }
                else{
                    if(dormitoryService.countDormitoryByBuildingId(id[i]) != 0){
                        return false;
                    }
                    else{
                       building.setStatus(0);
                        buildingDao.update(building);
                    }
                }
            }
        }
        return true;
    }

    public Long countBuildingByAdminId(Integer adminId){
        String hql = "select count(*) from Building b where b.adminId = ? ";
        Object[] param = new Object[1];
        param[0] = adminId;
        return buildingDao.count(hql, param);
    }

    public Long countBuildingByNumber(Integer number){
        String hql = "select count(*) from Building  b where b.status = 1 and b.number = ? ";
        Object[] param = new Object[1];
        param[0] = number;
        Long count = buildingDao.count(hql, param);

        return count;
    }

    public List<Building> listBuildingByProfessionAndSex(Integer department, Integer sex){
        String hql = "select b from Building b where b.status = 1 and  b.departmentId = ? and sex = ?";
        Object[] param = new Object[2];
        param[0] = department;
        param[1] = sex;
        return buildingDao.find(hql, param);
    }

}
