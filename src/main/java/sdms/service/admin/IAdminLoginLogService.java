package sdms.service.admin;


import sdms.entity.admin.AdminLoginLog;
import sdms.util.Page;

/**
 * Created by cheng on 2016/12/30.
 */
public interface IAdminLoginLogService {

    boolean addLoginLog(AdminLoginLog adminLoginLog);

    Page<AdminLoginLog> pageLoginLog(Integer adminId, Integer currentPage, Integer pageSize, String startTime, String endTime);

    AdminLoginLog findLastLog(String adminId);
}
