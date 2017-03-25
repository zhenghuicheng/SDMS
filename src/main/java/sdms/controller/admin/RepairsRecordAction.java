package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.RepairsRecordDto;
import sdms.entity.admin.RepairsRecord;
import sdms.entity.admin.Student;
import sdms.service.admin.IRepairsRecordService;
import sdms.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/5.
 */
@Controller
@RequestMapping(value = "/repairsRecord")
public class RepairsRecordAction {

    @Autowired
    private IRepairsRecordService repairsRecordService;

    @RequestMapping(value = "/index")
    public String index(){
        return "repairsRecord/index";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id){
        Map map = new HashMap<>();
        if( null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            if(repairsRecordService.deleteRepairsRecord(id)){
                map.put("message", true);
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
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer buildingId, Integer dormitoryId, String startTime, String endTime,
                         String name, Integer isRepairs, String number){
        Page<RepairsRecordDto>  page = repairsRecordService.pageRepairRecord(currentPage, pageSize,
                buildingId, dormitoryId, startTime, endTime, name, isRepairs, 0, number);

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss");
    }

    @ResponseBody
    @RequestMapping(value = "/user/search")
    public String userSearch(HttpServletRequest request,
                             @RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                             @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                             String startTime, String endTime,
                             Integer isRepairs, String number){
        Page<RepairsRecordDto> page;

        Student student = (Student) request.getSession().getAttribute("user");
        if(null == student){
            page = new Page<>();
        }
        else{
            page = repairsRecordService.pageRepairRecord(currentPage, pageSize, 0, 0, startTime, endTime, "", isRepairs, student.getId(), number);
        }
        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss");

    }

    @ResponseBody
    @RequestMapping(value = "/user/is")
    public String updateIs(Integer id , Integer state){
        Map map = new HashMap();
        if(null == id){
            map.put("message", false);
        }
        else{
            if(repairsRecordService.updateIsRepairs(id, state)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/user/update")
    public String updateRepairsRecord(RepairsRecord repairsRecord){
        Map map = new HashMap();
        if(null == repairsRecord){
            map.put("message", false);
        }
        else{
            if(repairsRecordService.updateRepairsRecord(repairsRecord)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/user/add")
    public String addRepairsRecord(HttpServletRequest request, RepairsRecord repairsRecord){
        Map map = new HashMap();
        Student student = (Student) request.getSession().getAttribute("user");
        if(null == repairsRecord || student == null){
            map.put("message", false);
        }
        else{
            if(student.getDormitoryId() == 0){
                map.put("message", false);
            }
            else{
                repairsRecordService.addRepairsRecord(repairsRecord, student.getId());
                map.put("message", true);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/user/delete")
    public String deleteUserRepairsRecord(HttpServletRequest request, Integer[] id){
        Map map = new HashMap();
        Student student = (Student) request.getSession().getAttribute("user");
        if(null == id || id.length == 0 || student == null){
            map.put("message", false);
        }
        else{
            if(repairsRecordService.deleteRepairsRecord(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }






}
