package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.SendWaterRecordDto;
import sdms.entity.admin.SendWaterRecord;
import sdms.entity.admin.Student;
import sdms.service.admin.ISendWaterRecordService;
import sdms.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/5.
 */
@Controller
@RequestMapping(value = "/sendWaterRecord")
public class SendWaterRecordAction {

    @Autowired
    private ISendWaterRecordService sendWaterRecordService;

    @RequestMapping(value = "/index")
    public String index(){
        return "sendWaterRecord/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(SendWaterRecord sendWaterRecord, Integer studentId){
        Map map = new HashMap<>();

        if( null == sendWaterRecord){
            map.put("message", false);
        }
        else{
            if(sendWaterRecordService.addSendWaterRecord(sendWaterRecord, studentId)){
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
                         Integer buildingId, Integer dormitoryId,
                         String startTime, String endTime,
                         String name, Integer isSend){
        Page<SendWaterRecordDto> page = sendWaterRecordService.pageSendWaterRecord(currentPage, pageSize, buildingId,
                dormitoryId, startTime, endTime, name, isSend, null);
        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss");
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id){
        Map map = new HashMap();
        if( null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            if(sendWaterRecordService.deleteSendWaterRecord(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/user/search")
    public String userSearch(HttpServletRequest request,
                             @RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                             @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                             String startTime, String endTime, Integer isSend){
        Student student = (Student) request.getSession().getAttribute("user");
        Page<SendWaterRecordDto> page;
        if(null == student){
            page = new Page<>();
        }
        else{
            page = sendWaterRecordService.pageSendWaterRecord(currentPage, pageSize,0, 0, startTime, endTime, "", isSend, student.getId());
        }

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss");
    }

    @ResponseBody
    @RequestMapping(value = "/user/update")
    public String updateSendWaterRecord(SendWaterRecord sendWaterRecord){
        Map map = new HashMap();
        if(null == sendWaterRecord){
            map.put("message", false);
        }
        else{
            if(sendWaterRecordService.updateSendWaterRecord(sendWaterRecord)){
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
    public String addSendWaterRecord(HttpServletRequest request, SendWaterRecord sendWaterRecord){
        Map map = new HashMap();
        Student student = (Student) request.getSession().getAttribute("user");
        if(null == sendWaterRecord || null == student){
            map.put("message", false);
        }
        else{
            if(student.getDormitoryId() == 0){
                map.put("message", false);
            }
            else{
                if(sendWaterRecordService.addSendWaterRecord(sendWaterRecord, student.getId())){
                    map.put("message", true);
                }
                else{
                    map.put("message", false);
                }
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/user/isSend")
    public String updateIsSend(Integer id){
        Map map = new HashMap();
        if( null == id){
            map.put("message", false);
        }
        else{
            if(sendWaterRecordService.updateIsSend(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/user/delete")
    public String deleteUserSendWaterRecord(HttpServletRequest request, Integer[] id){
        Map map = new HashMap();
        Student student = (Student) request.getSession().getAttribute("user");
        if(null == id || id.length == 0 || student == null){
            map.put("message", false);
        }
        else{
            if(sendWaterRecordService.deleteSendWaterRecord(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }
}
