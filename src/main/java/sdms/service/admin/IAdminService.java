package sdms.service.admin;


import sdms.entity.admin.Admin;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2016/12/27.
 */
public interface IAdminService {

    int addAdmin(Admin admin);

    List<Admin> findAdmin(String hql);

    /**
     * 验证管理员身份
     * @param name
     * @param password
     * @return
     */
    boolean findVerifyAdmin(String name, String password);

    Admin findByJobName(String jobNumber);

    Page<Admin> pageAdmin(Integer currentPage, Integer pageSize, String jobNumber, String name);

    boolean deleteAdmin(Integer[] id);

    Admin findById(Integer id);

    boolean updatePasswordByAdmin(Integer id);

    boolean updatePassword(String primitivePassword, String newPassword, Integer id);

    List<Admin> listAdmin();

    boolean updateAdmin(Admin admin);

    Long countAdminByJobNumber(String jobNumber);

    Long countAdminByName(String name);

    boolean updatePhone(Admin admin);
}
