package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.entity.admin.AdminLoginLog;
import sdms.service.admin.IAdminLoginLogService;
import sdms.util.Page;


/**
 * Created by cheng on 2017/1/3.
 */
@Controller
@RequestMapping(value = "/log")
public class AdminLogAction {

    @Autowired
    private IAdminLoginLogService adminLoginLogService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/admin/log";
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public String getLogData(Integer adminId, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                             @RequestParam(value = "currentPage", defaultValue = "1")int currentPage, String startTime, String endTime){

        Page<AdminLoginLog> page = adminLoginLogService.pageLoginLog(adminId, currentPage, pageSize, startTime, endTime);

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
