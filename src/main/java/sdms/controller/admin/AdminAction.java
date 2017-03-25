package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.entity.admin.Admin;
import sdms.entity.admin.AdminLoginLog;
import sdms.service.admin.IAdminLoginLogService;
import sdms.service.admin.IAdminService;
import sdms.service.admin.IBuildingService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2016/12/30.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminAction {

    @Autowired
    private IAdminLoginLogService adminLoginLogService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IBuildingService buildingService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "/first")
    public String first(){
        return "/admin/first";
    }

    @RequestMapping(value = "/two")
    public String two(){
        return "/admin/two";
    }

    @RequestMapping(value = "/three")
    public String three(){
        return "/admin/three";
    }

    @RequestMapping(value = "/test")
    public String test(){
        return "/admin/test";
    }

    @ResponseBody
    @RequestMapping(value = "/log")
    public String getLastlog(String adminId){
        Map data = new HashMap();
        if (adminId != null && !adminId.equals("")){
            AdminLoginLog adminLoginLog = adminLoginLogService.findLastLog(adminId);
            if (adminLoginLog != null){
                data.put("data", adminLoginLog);
            }
            else{
                data.put("data", "error");
            }
        }
        else{
            data.put("data", "error");
        }

        return JSON.toJSONString(data);
    }


    @RequestMapping(value = "/admin")
    public String admin(){
        return "/admin/admin";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(Admin admin){
        Map map = new HashMap();

        if( null == admin){
            map.put("message",false);
        }
        else{
            if(adminService.addAdmin(admin) == 0){
                map.put("message",true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize, String jobNumber, String name){
        return JSON.toJSONString(adminService.pageAdmin(currentPage, pageSize, jobNumber, name));
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String Delete(Integer[] id){
        Map map = new HashMap();
        List<String> list = new ArrayList<>();
        boolean status = true;

        if( null == id || id.length == 0 ){
            map.put("message",false);
        }
        else{
            for(int i = 0; i < id.length; i++){
                if(buildingService.countBuildingByAdminId(id[i]) != 0){
                    Admin admin = adminService.findById(id[i]);
                    list.add(admin.getJobNumber());
                    status = false;
                }
            }
            if(status == true){
                if( adminService.deleteAdmin(id)){
                    map.put("message",true);
                }
                else{
                    map.put("message",false);
                }
            }
            else{
                map.put("message", false);
                map.put("number", list);
            }

        }

        return JSON.toJSONString(map);

    }


    @ResponseBody
    @RequestMapping(value = "/reset")
    public String resetPassword(Integer id){
        Map map = new HashMap();
        if( null == id){
            map.put("message",false);
        }
        else{
            if( adminService.updatePasswordByAdmin(id)){
                map.put("message",true);
            }
            else{
                map.put("message",false);
            }
        }
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword")
    public String updatePassword(HttpServletRequest request, String oldPassword, String newPassword){
        Map map = new HashMap();
        if( null == oldPassword || oldPassword.equals("")){
            map.put("message",false);
        }
        if( null == newPassword || newPassword.equals("")){
            map.put("message",false);
        }
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if(adminService.updatePassword(oldPassword, newPassword, admin.getId())){
            map.put("message",true);
        }
        else{
            map.put("message",false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String list(){
        List<Admin> list = adminService.listAdmin();
        return JSON.toJSONString(list);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String updateAdmin(Admin admin){
        Map map = new HashMap();
        if(null == admin){
            map.put("message", false);
        }
        else{
            if(adminService.updateAdmin(admin)){
                map.put("message", true);
            }
            else {
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/validateJobNumber")
    public String validateJobNumber(String jobNumber){
        Long count = adminService.countAdminByJobNumber(jobNumber);
        Map map = new HashMap();
        if(0 == count){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/validateName")
    public String validateName(String name){
        Long count = adminService.countAdminByName(name);
        Map map = new HashMap();
        if(0 == count){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public String getAdmin(HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        if (admin == null || admin.getId() == null){
           admin = new Admin();
        }
        else{
            admin = adminService.findById(admin.getId());
            admin.setPassword("");
        }

        return JSON.toJSONString(admin);
    }


    @ResponseBody
    @RequestMapping(value = "/updatePhone")
    public String updatePhone(Admin admin){
        Map map = new HashMap();
        if (null == admin){
            map.put("message", false);
        }
        else{
            if (adminService.updatePhone(admin)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }


}
