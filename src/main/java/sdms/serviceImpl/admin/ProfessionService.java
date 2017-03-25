package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.ProfessionDao;
import sdms.dto.admin.ProfessionDto;
import sdms.entity.admin.Profession;
import sdms.service.admin.IProfessionService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/17.
 */
@Service
public class ProfessionService implements IProfessionService{

    @Autowired
    private ProfessionDao professionDao;

    @Override
    public Profession findProfessionByNumber(String number) {
        if( null != number || number.equals("")){
            return null;
        }
        else{
            String hql = "select a from Profession a where a.number = ?";
            Object[] params = new Object[1];
            params[0] = number;

            List<Profession> profession = professionDao.find(hql, params);

            if (null != profession && profession.size() > 0){
                return profession.get(0);
            }
            else{
                return null;
            }
        }
    }

    public boolean addProfession(Profession profession){

        if( null == profession){
            return false;
        }
        else{
            profession.setStatus(1);
            if( professionDao.save(profession) != null){
                return true;
            }
            else{
                return false;
            }
        }

    }

    public Page<ProfessionDto> pageProfession(Integer currentPage, Integer pageSize, Integer departmentId, String name){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        hql.append( "select p.id as id,p.number as number,p.classNumber as classNumber,p.remark as remark," +
                "p.name as name,d.name as departmentName, d.id as departmentId"+
                " from profession p join department d on p.departmentId = d.id " +
                "where p.status = 1 ");
        countHql.append( "select count(*) " +
                " from profession p join department d on p.departmentId = d.id " +
                "where p.status = 1 ");
        /*String hql = "select p.id as id,p.number as number,p.classNumber as classNumber,p.remark as remark," +
                "p.name as name,d.name as departmentName, d.id as departmentId"+
                " from profession p join department d on p.departmentId = d.id " +
                "where p.status = 1";
        String countHql = "select count(*) from Profession a where a.status = 1";*/
        Object[] params = new Object[2];
        if(null != departmentId && departmentId != 0){
            hql.append(" and  p.departmentId = ? ");
            countHql.append(" and p.departmentId = ? ");
            params[0] = departmentId;
        }
        if(null != name && !name.equals("")){
            hql.append(" and p.name like ? ");
            countHql.append(" and p.name like ? ");
            params[1] = "%" + name + "%";
        }
        Long count = professionDao.countSQL(countHql.toString(), params);
        List<ProfessionDto> list = professionDao.pageSQL(hql.toString(), params, currentPage, pageSize, ProfessionDto.class);
        Page<ProfessionDto> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setTotal(count);
        page.setResults(list);

        return page;
    }

    @Override
    public boolean updateProfession(Profession profession) {
        if (null == profession || null == profession.getId()){
            return false;
        }
        else{
            try{
                professionDao.update(profession);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
    }

    @Override
    public boolean deleteProfession(Integer[] id) {

        if (null == id || id.length == 0){
            return false;
        }
        else{
            Profession profession;

            for (int i = 0; i < id.length; i++){
                try{
                    profession = findProfessionById(id[i]);
                    profession.setStatus(0);
                    professionDao.update(profession);
                }
                catch (Exception e){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public Profession findProfessionById(Integer id) {

        if (id == null){
            return null;
        }
        else{
            String hql = "select a from Profession a where a.id = ?";
            Object[] params = new Object[1];
            params[0] = id;

            try{
                return professionDao.find(hql, params).get(0);
            }
            catch (Exception e){
                return null;
            }
        }
    }

    public List<Profession> listByDepartment(Integer departmentId){
        if( null == departmentId){
            return null;
        }
        else{
            String hql = "select a from Profession a where a.status =1 and a.departmentId = ?";
            Object[] params = new Object[1];
            params[0] = departmentId;
            return professionDao.find(hql, params);
        }
    }

    public Long countProfessionByName(String name){
        String hql = "select count(*) from Profession p where p.status = 1  and p.name = ?";
        Object[] param = new Object[1];
        param[0] = name;
        return professionDao.count(hql, param);
    }

    public Long countProfessionByNumber(String number){
        String hql = "select count(*) from Profession p where p.status = 1 and p.number = ?";
        Object[] param = new Object[1];
        param[0] = number;
        return professionDao.count(hql, param);
    }

    public Long countProfessionByDepartmentId(Integer id){
        String hql = "select count(*) from Profession p where p.status = 1 and p.departmentId = ?";
        Object[] param = new Object[1];
        param[0] = id;
        return professionDao.count(hql, param);
    }
}
