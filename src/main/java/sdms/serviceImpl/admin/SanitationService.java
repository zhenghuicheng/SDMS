package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.SanitationDao;
import sdms.dto.admin.SanitationDto;
import sdms.entity.admin.Sanitation;
import sdms.service.admin.ISanitationService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/2/2.
 */
@Service
public class SanitationService implements ISanitationService{

    @Autowired
    private SanitationDao sanitationDao;

    public Sanitation findSanitationById(Integer id){
        if(null == id ){
            return null;
        }
        else{
            String hql = "select a from Sanitation a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<Sanitation> list = sanitationDao.find(hql, param);
            if( null == list || list.size() == 0 ){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    public boolean addSanitation(Sanitation sanitation){
        if( null == sanitation){
            return false;
        }
        else{
            sanitation.setStatus(1);
            sanitationDao.save(sanitation);
            return true;
        }
    }

    public boolean deleteSanitation(Integer[] id){
        if( null == id || id.length == 0){
            return false;
        }
        else{
            Sanitation sanitation;
            for( int i = 0; i < id.length; i++){
                sanitation = findSanitationById(id[i]);
                sanitation.setStatus(0);
                sanitationDao.update(sanitation);
            }
            return true;
        }
    }

    public boolean updateSanitation(Sanitation sanitation){
        if( null == sanitation){
            return false;
        }
        else{
            Sanitation oldSanitation = findSanitationById(sanitation.getId());
            oldSanitation.setStartTime(sanitation.getStartTime());
            oldSanitation.setEndTime(sanitation.getEndTime());
            oldSanitation.setDegree(sanitation.getDegree());
            sanitationDao.update(oldSanitation);
            return true;
        }
    }

    public Page<SanitationDto> pageSanitation(Integer currentPage, Integer pageSize,
                                              Integer buildingId, Integer dormitoryId,
                                              String startTime, String endTime, Integer degree){

        StringBuilder hql = new StringBuilder();
        hql.append("select w.id as id, w.dormitory_id as dormitoryId, w.status as status, " +
                "w.start_time as startTime, w.end_time as endTime, w.degree as degree, " +
                "d.building_number as buildingNumber, d.number as dormitoryNumber " +
                "from dormitory d join sanitation w on d.id = w.dormitory_id " +
                "where w.status = 1");
        StringBuilder countHql = new StringBuilder();
        countHql.append("select count(*) " +
                "from dormitory d join sanitation w on d.id = w.dormitory_id " +
                "where w.status = 1");
        Object[] param = new Object[5];
        if(null != buildingId && buildingId != 0){
            hql.append(" and d.building_id = ?");
            countHql.append(" and d.building_id = ?");
            param[0] = buildingId;
        }
        if(null != dormitoryId && dormitoryId != 0){
            hql.append(" and w.dormitory_id = ?");
            countHql.append(" and w.dormitory_id = ?");
            param[1] = dormitoryId;
        }
        if(null != startTime && !startTime.equals("")){
            hql.append(" and w.start_time >= ?");
            countHql.append(" and w.start_time >= ?");
            param[2] = startTime;
        }
        if(null != endTime && !endTime.equals("")){
            hql.append(" and w.end_time <= ?");
            countHql.append(" and w.end_time <= ?");
            param[3] = endTime;
        }
        if(null != degree && 0 != degree){
            hql.append(" and  w.degree = ? ");
            countHql.append(" and w.degree = ?");
            param[4] = degree;
        }

        List<SanitationDto> list = sanitationDao.pageSQL(hql.toString(), param, currentPage, pageSize, SanitationDto.class);
        Long count = sanitationDao.countSQL(countHql.toString(), param);
        Page<SanitationDto> page = new Page<>();
        page.setResults(list);
        page.setCurPageNo(currentPage);
        page.setTotal(count);

        return page;
    }


}
