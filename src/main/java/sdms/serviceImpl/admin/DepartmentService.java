package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.DepartmentDao;
import sdms.entity.admin.Department;
import sdms.service.admin.IDepartmentService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/14.
 */
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public boolean addDepartment(Department department) {
        if(null == findDepartmentByNumber(department.getNumber())){
            department.setStatus(1);
            if(null != departmentDao.save(department)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public Department findDepartmentByNumber(String number) {
        String hql = "select a from Department as a where a.status = 1 and a.number = ?";
        Object[] param = new Object[1];
        param[0] = number;
        List<Department> list = departmentDao.find(hql, param);
        if (list.isEmpty() || list.size() < 0){
            return null;
        }
        else{
            return list.get(0);
        }
    }

    public Page<Department> pageDepartment(Integer currentPage, Integer pageSize, Integer number, String name){
        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        hql.append("select a from Department a where a.status = 1 ");
        countHql.append("select count(*) from Department a where a.status = 1");
        Object[] parmas = new Object[2];
        if(null != number && number != 0){
            hql.append(" and a.number like ?");
            countHql.append(" and a.number like ? ");
            parmas[0] = "%" + number + "%";
        }
        if(null != name && !name.equals("")){
            hql.append(" and a.name like ? ");
            countHql.append(" and a.name like ? ");
            parmas[1] = "%" + name + "%";
        }
        Long count = departmentDao.count(countHql.toString(), parmas);
        List<Department> list = departmentDao.page(hql.toString(), parmas, currentPage, pageSize);
        Page<Department> page = new Page<>();
        page.setTotal(count);
        page.setCurPageNo(currentPage);
        page.setResults(list);

        return page;
    }

    @Override
    public boolean updateDepartment(Department department) {
        if (null == department || null == department.getId()){
            return false;
        }
        else{

            try{
                department.setStatus(1);
                departmentDao.update(department);
                return true;
            }
            catch (Exception e){
                return false;
            }

        }

    }

    public Department findDepartmentById(Integer id){
        String hql = "select a from Department as a where a.id = ?";
        Object[] param = new Object[1];
        param[0] = id;
        List<Department> list = departmentDao.find(hql, param);
        if (list.isEmpty() || list.size() < 0){
            return null;
        }
        else{
            return list.get(0);
        }
    }

    @Override
    public boolean deleteDepartment(Integer[] id) {

        if( id == null || id.length ==0){
            return false;
        }
        else{

            Department department;
            for (int i = 0; i < id.length; i++){
                department = findDepartmentById(id[i]);
                try{
                    department.setStatus(0);
                    departmentDao.update(department);
                }
                catch (Exception e){
                    return false;
                }

            }

            return true;
        }
    }

    @Override
    public List<Department> listDepartment() {
        String hql = "select a from Department a where a.status = 1";
        Object[] params = new Object[0];

        return departmentDao.find(hql, params);
    }

    public Long countDepartmentByName(String name){
        String hql = "select count(*) from Department d where d.status = 1 and d.name = ?";
        Object[] param = new Object[1];
        param[0] = name;
        return departmentDao.count(hql, param);
    }

    public Long countDepartmentByNumber(String number){
        String hql = "select count(*) from Department d where d.status = 1 and d.number = ? ";
        Object[] param = new Object[1];
        param[0] = number;
        return departmentDao.count(hql, param);
    }


}
