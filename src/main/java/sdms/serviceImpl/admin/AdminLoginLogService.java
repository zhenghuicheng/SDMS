package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.AdminLoginLogDao;
import sdms.entity.admin.AdminLoginLog;
import sdms.service.admin.IAdminLoginLogService;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2016/12/30.
 */
@Service
public class AdminLoginLogService implements IAdminLoginLogService {

    @Autowired
    private AdminLoginLogDao adminLoginLogDao;

    @Override
    public boolean addLoginLog(AdminLoginLog adminLoginLog) {
        if (adminLoginLog != null){
            adminLoginLogDao.save(adminLoginLog);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Page<AdminLoginLog> pageLoginLog(Integer adminId, Integer currentPage, Integer pageSize, String startTime, String endTime) {
        StringBuilder hql = new StringBuilder("");
        StringBuilder countHql = new StringBuilder("");
        hql.append("select a from AdminLoginLog a where a.adminId = ? ");
        countHql.append("select count(*) from AdminLoginLog a where a.adminId = ? ");


        /*String hql = "select a from AdminLoginLog a " +
                "where a.adminId = ? and a.loginTime >= ? and a.loginTime <= ? order by a.loginTime desc";
        String countHql = "select count(*) from AdminLoginLog a " +
                "where a.adminId = ? and a.loginTime >= ? and a.loginTime <= ? order by a.loginTime desc";*/
        Object[] param = new Object[3];

        param[0] = adminId;

        if (startTime != null && !startTime.equals("")){
            hql.append(" and a.loginTime >= ? ");
            countHql.append(" and a.loginTime >= ? ");
            param[1] = startTime;
        }
        if (endTime !=null && !endTime.equals("")){
            hql.append(" and a.loginTime <= ? ");
            countHql.append(" and a.loginTime <= ? ");
            if( startTime != null && !startTime.equals("")){
                param[2] = endTime;
            }
            else{
                param[1] = endTime;
            }
        }
        hql.append(" order by a.loginTime desc");
        countHql.append(" order by a.loginTime desc");



        List<AdminLoginLog> adminLoginLogList = adminLoginLogDao.page(hql.toString(), param, currentPage, pageSize);
        Long count = adminLoginLogDao.count(countHql.toString(), param);
        Page<AdminLoginLog> page = new Page<>();
        page.setResults(adminLoginLogList);
        page.setCurPageNo(currentPage);
        page.setTotal(count);


        return page;
    }

    @Override
    public AdminLoginLog findLastLog(String adminId) {
        String hql = "select a from AdminLoginLog as a " +
                "where a.adminId = ? order by a.loginTime desc";
        Object[] params = new Object[1];
        params[0] = Integer.parseInt(adminId);
        List<AdminLoginLog> list = adminLoginLogDao.page(hql, params, 1, 2);
        if(list != null && list.size() == 2){
            return list.get(1);
        }
        else{
            return null;
        }
    }


}
