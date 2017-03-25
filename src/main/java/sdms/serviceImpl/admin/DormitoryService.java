package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.DormitoryDao;
import sdms.dto.admin.DormitoryDto;
import sdms.dto.admin.DormitoryStatisticsDto;
import sdms.entity.admin.Building;
import sdms.entity.admin.Dormitory;
import sdms.service.admin.IBuildingService;
import sdms.service.admin.IDormitoryService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/30.
 */
@Service
public class DormitoryService implements IDormitoryService{

    @Autowired
    private DormitoryDao dormitoryDao;

    @Autowired
    private IBuildingService buildingService;

    public Dormitory findDormitoryById(Integer id){
        String hql = "select a from Dormitory a where a.id = ?";
        Object[] param = new Object[1];
        param[0] = id;
        List<Dormitory> list = dormitoryDao.find(hql, param);
        if( null == list || list.size() ==0 ){
            return null;
        }
        else{
            return list.get(0);
        }
    }

    public Dormitory findDormitoryByNumber(Integer building, Integer number){
        String hql = "select a from Dormitory a where a.buildingId = ? and a.number = ?";
        Object[] param = new Object[2];
        param[0] = building;
        param[1] = number;
        List<Dormitory> list = dormitoryDao.find(hql, param);
        if(null == list || list.size() == 0){
            return null;
        }
        else{
            return list.get(0);
        }

    }

    public boolean addDormitory(Dormitory dormitory){
        if( null == dormitory){
            return false;
        }
        else{
            if(findDormitoryByNumber(dormitory.getBuildingId(), dormitory.getNumber()) == null){
                dormitory.setStatus(1);
                Building building = buildingService.findBuildingById(dormitory.getBuildingId());
                dormitory.setStudentNumber(0);
                dormitory.setBuildingNumber(building.getNumber());
                dormitoryDao.save(dormitory);
                return true;
            }
            else{
                return false;
            }

        }
    }

    public Page<DormitoryDto> pageDormitory(Integer currentPage, Integer pageSize, Integer buildingId, Integer floor, Integer dormitoryId,
                                            String type, Integer studentNumber){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        Object[] param = new Object[4];

        hql.append("select d.id as id, d.building_id as buildingId, b.number as buildingNumber, " +
                        "d.floor as floor, d.number as number, d.bed_number as bedNumber, d.student_number as studentNumber, d.status as status " +
                        "from dormitory d join building b on d.building_id = b.id " +
                        "where d.status = 1");
        countHql.append("select count(*) " +
                "from dormitory d join building b on d.building_id = b.id " +
                "where d.status = 1");

        if(null != buildingId && buildingId != 0){
            hql.append(" and d.building_id = ?");
            countHql.append(" and d.building_id = ? ");
            param[0] = buildingId;
        }
        if(null != floor && floor != 0){
            hql.append(" and d.floor = ?");
            countHql.append(" and d.floor = ? ");
            param[1] = floor;
        }
        if(null != dormitoryId && dormitoryId != 0){
            hql.append(" and d.id = ? ");
            countHql.append(" and d.id = ? ");
            param[2] = dormitoryId;
        }
        if(!type.equals("0")){
            hql.append(" and d.student_number ");
            countHql.append(" and d.student_number ");
            switch (type){
                case "lt" :
                    hql.append(" <= ? ");
                    countHql.append(" <= ? ");
                    break;
                case "gt" :
                    hql.append(" >= ? ");
                    countHql.append(" >= ? ");
                    break;
                case "=" :
                    hql.append(" = ? ");
                    countHql.append(" = ? ");
                    break;
                case "!=" :
                    hql.append(" != ? ");
                    countHql.append(" != ? ");
                    break;
            }
            param[3] = studentNumber;
        }

        hql.append(" order by b.number,d.number ");


       /* String hql = "select d.id as id, d.building_id as buildingId, b.number as buildingNumber, " +
                "d.floor as floor, d.number as number, d.bed_number as bedNumber, d.student_number as studentNumber, d.status as status " +
                "from dormitory d join building b on d.building_id = b.id " +
                "where d.status = 1 order by b.number,d.number";
        String countHql = "select count(*) " +
                "from dormitory d join building b on d.building_id = b.id " +
                "where d.status = 1";*/

        Long count = dormitoryDao.countSQL(countHql.toString(), param);
        List<DormitoryDto> list = dormitoryDao.pageSQL(hql.toString(), param, currentPage, pageSize, DormitoryDto.class);
        Page<DormitoryDto> page = new Page<>();
        page.setTotal(count);
        page.setResults(list);
        page.setCurPageNo(currentPage);

        return page;
    }

    public boolean addAllDormitory(Integer buildingId, Integer buildingNumber, Integer floor, Integer floorNumber, Integer bedNumber){


        for(int i = 1; i <= floor; i++){
            for(int j = 1; j <= floorNumber; j++){
                Dormitory dormitory = new Dormitory();
                dormitory.setBuildingId(buildingId);
                dormitory.setStudentNumber(0);
                dormitory.setNumber(i * 100 + j);
                dormitory.setStatus(1);
                dormitory.setFloor(i);
                dormitory.setBedNumber(bedNumber);
                dormitory.setBuildingNumber(buildingNumber);

                dormitoryDao.save(dormitory);

            }
        }

        return true;
    }

    public List<Dormitory> listDormitoryByBuildingId(Integer buildingId){
        String hql = "select a from Dormitory a where a.status = 1 and a.buildingId = ? order by a.status";
        Object[] param = new Object[1];
        param[0] = buildingId;
        List<Dormitory> list = dormitoryDao.find(hql, param);
        return list;
    }

    public boolean updateDormitory(Dormitory dormitory){
        if(null == dormitory){
            return false;
        }
        else{
            Dormitory oldDormitory = findDormitoryById(dormitory.getId());
            if(null == oldDormitory){
                return false;
            }
            else{
                if(dormitory.getBedNumber() < oldDormitory.getStudentNumber()){
                    return false;
                }
                else{
                    oldDormitory.setBedNumber(dormitory.getBedNumber());
                    return true;
                }
            }
        }

    }

    public boolean deleteDormitory(Integer[] id){
        if(null == id || id.length == 0){
            return false;
        }
        else{
            Dormitory dormitory;
            for(int i = 0; i < id.length; i++){
                dormitory = findDormitoryById(id[i]);
                if(null == dormitory || dormitory.getStudentNumber() != 0){
                    return false;
                }
                else{
                    dormitory.setStatus(0);
                    dormitoryDao.update(dormitory);
                }
            }
        }

        return true;
    }

    public Long countDormitoryByBuildingId(Integer buildingId){
        String hql = "select count(*) from Dormitory d where d.buildingId = ?";
        Object[] param = new Object[1];
        param[0] =buildingId;
        return dormitoryDao.count(hql, param);
    }

    public Long countDormitoryByBuildingidAndNumber(Integer buildingId, Integer number){
        String hql = "select count(*) from Dormitory d where d.buildingId = ? and d.number= ? ";
        Object[] param = new Object[2];
        param[0] = buildingId;
        param[1] = number;
        return dormitoryDao.count(hql, param);
    }

    public Long countDormitoryRemainBedNumber(Integer buildingId){
        String bedhql = "select sum(d.bed_number) from dormitory d where d.status = 1 and d.building_id = ? group by d.building_id ";
        String studenthql = "select sum(d.student_number) from dormitory d where d.status = 1 and d.building_id = ? group by d.building_id ";
        Object[] param = new Object[1];
        param[0] = buildingId;
        Long bedNumber = dormitoryDao.sumSQL(bedhql, param);
        Long studentNumber = dormitoryDao.sumSQL(studenthql, param);
        return bedNumber - studentNumber;
    }

    public List<Dormitory> listDormitoryByBuildingIdAndFloor(Integer buildingId, Integer floor){
        String hql = "select a from Dormitory a where a.status = 1 and a.buildingId = ? and a.floor = ? order by a.status";
        Object[] param = new Object[2];
        param[0] = buildingId;
        param[1] = floor;
        List<Dormitory> list = dormitoryDao.find(hql, param);

        return list;
    }

    public Page<DormitoryStatisticsDto> statisticsDormitory(Integer currentPage, Integer pageSize, Integer buildingId, String type, Integer studentNumber){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        Object[] param = new Object[2];
        if(null != buildingId && buildingId != 0){
            hql.append("select d.building_id as buildingId, d.building_number as buildingNumber, " +
                    "d.floor as floor,sum(d.student_number) as studentNumber, sum(d.bed_number) as bedNumber " +
                    "from dormitory d where d.status = 1 and d.building_id = ? group by d.floor");
            countHql.append("select count(*) from ( select * from dormitory d where d.status = 1 and d.building_id = ? group by d.building_id ");
            param[0] = buildingId;
        }
        else{
            hql.append("select d.building_id as buildingId, d.building_number as buildingNumber, " +
                    "sum(d.student_number) as studentNumber, sum(d.bed_number) as bedNumber " +
                    "from dormitory d where d.status = 1 group by d.building_id");
            countHql.append("select count(*) from ( select * from dormitory d where d.status = 1 group by d.building_id ");
        }

        if(!type.equals("0")){
            hql.append(" having sum(d.student_number) ");
            countHql.append(" having sum(d.student_number) ");
            switch (type){
                case "lt" :
                    hql.append(" <= ? ");
                    countHql.append(" <= ? ");
                    break;
                case "gt" :
                    hql.append(" >= ? ");
                    countHql.append(" >= ? ");
                    break;
                case "=" :
                    hql.append(" = ? ");
                    countHql.append(" = ? ");
                    break;
                case "!=" :
                    hql.append(" != ? ");
                    countHql.append(" != ? ");
                    break;
            }
            param[1] = studentNumber;
        }

        countHql.append(" ) as a ");

        Long count = dormitoryDao.countSQL(countHql.toString(), param);
        List<DormitoryStatisticsDto> list = dormitoryDao.pageSQL(hql.toString(), param, currentPage, pageSize, DormitoryStatisticsDto.class);
        Page<DormitoryStatisticsDto> page = new Page<>();
        page.setTotal(count);
        page.setResults(list);
        page.setCurPageNo(currentPage);

        return page;
    }

}
