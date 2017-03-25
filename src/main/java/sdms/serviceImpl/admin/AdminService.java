package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.AdminDao;
import sdms.entity.admin.Admin;
import sdms.service.admin.IAdminService;
import sdms.util.CommonsUtil;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2016/12/27.
 */
@Service
public class AdminService implements IAdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BuildingService buildingService;

    @Override
    public int addAdmin(Admin admin) {

        //查询该工号是否已经存在
        if ( findByJobName(admin.getJobNumber())!=null){
            return 1;
        }

        //对密码加密
        admin.setPassword(CommonsUtil.enPassword(admin.getJobNumber()));
        admin.setStatus(1);
        admin.setRole(1);
         if( adminDao.save(admin) != null){
             return 0;
         }
         else{
             return 2;
         }
    }

    @Override
    public List<Admin> findAdmin(String hql) {
        return adminDao.find(hql);
    }

    public Admin findByJobName(String jobNumber){

        String hql = "select a from Admin as a where a.jobNumber = ?";
        Object[] param = new Object[1];
        param[0] = jobNumber;
        List<Admin> list = adminDao.find(hql, param);
        if (list.isEmpty() || list.size() < 0){
            return null;
        }
        else{
            return list.get(0);
        }
    }

    public boolean  findVerifyAdmin(String name, String password){
        String hql = "select a from Admin as a where a.jobNumber = ?";
        Object[] param = new Object[1];
        param[0] = name;
        List<Admin> list = adminDao.find(hql,param);
        if (list.isEmpty()){
            return false;
        }
        else{
            Admin admin = list.get(0);
            String enPassword = CommonsUtil.enPassword(password);
            if(admin.getPassword().equals(enPassword)){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public Page<Admin> pageAdmin(Integer currentPage, Integer pageSize, String jobNumber, String name){

        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();

        hql.append("select a from Admin a where a.status = 1 ");
        countHql.append("select count(*) from Admin a where a.status = 1 ");
        Object[] param = new Object[1];

        if( jobNumber != null && !jobNumber.equals("")){
            hql.append(" and a.jobNumber like ?");
            countHql.append(" and a.jobNumber like ?");
            param[0] = "%" + jobNumber + "%";
        }
        if( name != null && !name.equals("")){
            hql.append(" and a.name like ?");
            countHql.append(" and a.name like ?");
            param[0] = "%" + name + "%";
        }

        /*String hql = "select a from Admin a where a.status = 1";
        String countHql = "select count(*) from Admin a where a.status = 1";*/

        Long count = adminDao.count(countHql.toString(), param);
        List<Admin> list = adminDao.page(hql.toString(), param, currentPage, pageSize);
        if (null != list &&  list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                Admin admin = list.get(i);
                admin.setPassword(null);
            }
        }
        Page<Admin> page = new Page<>();
        page.setCurPageNo(currentPage);
        page.setResults(list);
        page.setTotal(count);

        return page;
    }

    public Admin findById(Integer id){
        String hql = "select a from Admin a where a.id = ?";
        Object[] param = new Object[1];
        param[0] = id;
        List<Admin> list = adminDao.find(hql, param);
        if( list == null || list.size() == 0){
            return null;
        }
        else{
            return list.get(0);
        }
    }

    public boolean deleteAdmin(Integer[] id){

        if( null == id || id.length == 0){
            return false;
        }
        else{
            Admin admin;
            for(int i = 0; i < id.length; i++){
                admin = findById(id[i]);
                if( admin == null){
                    return false;
                }
                else{
                    if(buildingService.countBuildingByAdminId(id[i]) != 0){
                        return false;
                    }
                    else{
                        admin.setStatus(0);
                        adminDao.update(admin);
                    }

                }
            }

            return true;
        }

    }

    public boolean updatePasswordByAdmin(Integer id){
        Admin admin;
        admin = findById(id);
        if( null == id){
            return false;
        }
        else{
            if( null == admin){
                return false;
            }
            else{
                admin.setPassword(CommonsUtil.enPassword(admin.getJobNumber()));
                adminDao.update(admin);
                return true;
            }
        }
    }

    public boolean updatePassword(String primitivePassword, String newPassword, Integer id){


        if( null == primitivePassword || primitivePassword.equals("")){
            return false;
        }
        if( null == newPassword || newPassword.equals("")){
            return false;
        }
        if( null == id){
            return false;
        }

        primitivePassword = CommonsUtil.enPassword(primitivePassword);

        Admin admin = findById(id);
        if( admin.getPassword().equals(primitivePassword)){
            admin.setPassword(CommonsUtil.enPassword(newPassword));
            adminDao.update(admin);
            return true;
        }
        else{
            return false;
        }

    }

    public List<Admin> listAdmin(){
        String hql = "select a from Admin a where a.status = 1";
        Object[] param = new Object[0];
        List<Admin> list = adminDao.find(hql, param);
        return list;
    }

    /**
     * 系统管理员修改管理员信息
     * @param admin
     * @return
     */
    public boolean updateAdmin(Admin admin){
        if(null == admin){
            return false;
        }
        else{
            Admin oldAdmin = findById(admin.getId());
            if(null == oldAdmin){
                return false;
            }
            else{
                oldAdmin.setJobNumber(admin.getJobNumber());
                oldAdmin.setPhone(admin.getPhone());
                oldAdmin.setName(admin.getName());
                adminDao.update(oldAdmin);
                return true;
            }
        }
    }

    public Long countAdminByJobNumber(String jobNumber){
        String hql = "select count(*) from Admin a where a.status = 1 and a.jobNumber = ?";
        Object[] param = new Object[1];
        param[0] = jobNumber;
        return adminDao.count(hql, param);
    }

    public Long countAdminByName(String name){
        String hql = "select count(*) from Admin a where a.status = 1 and a.name = ?";
        Object[] param = new Object[1];
        param[0] = name;
        return adminDao.count(hql, param);
    }

    public boolean updatePhone(Admin admin){
        if (null == admin){
            return false;
        }
        else{
            Admin oldAdmin = findById(admin.getId());
            if (null == oldAdmin){
                return false;
            }
            else{
                oldAdmin.setPhone(admin.getPhone());
                adminDao.update(oldAdmin);
            }
        }

        return true;
    }

}
