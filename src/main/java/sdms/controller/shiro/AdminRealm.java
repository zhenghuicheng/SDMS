package sdms.controller.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import sdms.entity.admin.Admin;
import sdms.entity.admin.Student;
import sdms.service.admin.IAdminService;
import sdms.service.admin.IStudentService;

/**
 * Created by cheng on 2016/12/30.
 */
public class AdminRealm extends AuthorizingRealm {

    private static Logger logger = Logger.getLogger(AdminRealm.class);

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IStudentService studentService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String role = username.split(",")[1];

        if( role.equals("1")){
            Admin admin = adminService.findByJobName(username.split(",")[0]);
            if(admin.getRole() == 0){
                authorizationInfo.addRole("sysadmin");
            }
            else if(admin.getRole() == 1){
                authorizationInfo.addRole("admin");
            }
            else{
                authorizationInfo.addRole("superadmin");
            }


        }
        else{
            authorizationInfo.addRole("student");
        }

        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        String userName = token.getUsername();
        String[] name = userName.split(",");
        String role = name[1];
        if(role.equals("1")){
            Admin admin = adminService.findByJobName(name[0]);
            if (admin != null){
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, admin.getPassword(), getName());

                return authenticationInfo;
            }
            else{
                return null;
            }
        }
        else{
            Student student = studentService.findStudentByNumber(name[0]);
            if(null != student){
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, student.getPassword(), getName());

                return authenticationInfo;
            }
            else{
                return null;
            }
        }




    }
}
