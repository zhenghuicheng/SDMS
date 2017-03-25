package sdms.controller.user;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.*;
import sdms.entity.admin.Dormitory;
import sdms.entity.admin.Student;
import sdms.service.admin.*;
import sdms.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/4.
 */
@Controller
@RequestMapping(value = "/user")
public class UserAction {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IVisitRecordService visitRecordService;

    @Autowired
    private ISanitationService sanitationService;

    @Autowired
    private IWaterRecordService waterRecordService;

    @Autowired
    private IElectricRecordService electricRecordService;

    @Autowired
    private IDormitoryService dormitoryService;


    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/first")
    public String first(){
        return "admin/first";
    }

    @RequestMapping(value = "/sendWaterRecord")
    public String sendWaterRecord(){
        return "user/sendWaterRecord";
    }

    @RequestMapping(value = "/repairsRecord")
    public String repairsRecord(){
        return "user/repairsRecord";
    }

    @RequestMapping(value = "/visitRecord")
    public String visitRecord(){
        return "user/visitRecord";
    }

    @RequestMapping(value = "/sanitation")
    public String sanitation(){
        return "user/sanitation";
    }

    @RequestMapping(value = "/waterRecord")
    public String waterRecord(){
        return "user/waterRecord";
    }

    @RequestMapping(value = "/electricRecord")
    public String electricRecord(){
        return "user/electricRecord";
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword")
    public String updatePassword(HttpServletRequest request, String oldPassword, String newPassword){
        Map map = new HashMap();

        Student student = (Student) request.getSession().getAttribute("user");

        if(null == student){
            map.put("message", false);
        }
        else{
            if(studentService.updatePassword(student.getId(), oldPassword, newPassword)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getStudent")
    public String getStudent(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("user");
        StudentDto studentDto;
        if(null == student){
            studentDto = new StudentDto();
        }
        else{
            studentDto = studentService.findStudentDtoById(student.getId());

        }
        return JSON.toJSONString(studentDto);
    }

    @ResponseBody
    @RequestMapping(value = "/visitRecordSearch")
    public String visitRecordSearch(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
             @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
            HttpServletRequest request, String startTime, String endTime, String visitor, String visitedName){
        Student student = (Student) request.getSession().getAttribute("user");
        Page<VisitRecordDto> page;
        if(null == student){
            page = visitRecordService.pageVisitRecord(currentPage, pageSize, 0, 0, startTime, endTime, visitor, visitedName);
        }
        else{
            page = visitRecordService.pageVisitRecord(currentPage, pageSize, 0, student.getDormitoryId(), startTime, endTime, visitor, visitedName);
        }

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss");
    }

    @ResponseBody
    @RequestMapping(value = "/sanitationSearch")
    public String sanitationSearch(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                   HttpServletRequest request, String startTime, String endTime, Integer degree){
        Student student = (Student) request.getSession().getAttribute("user");
        Page<SanitationDto> page;
        if (null == student){
            page = sanitationService.pageSanitation(currentPage, pageSize, 0, 0, startTime, endTime, degree);
        }
        else{
            page = sanitationService.pageSanitation(currentPage, pageSize, 0, student.getDormitoryId(), startTime, endTime, degree);
        }

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd");

    }

    @ResponseBody
    @RequestMapping(value = "/waterRecordSearch")
    public String waterRecordSearch(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                                    @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                    HttpServletRequest request, String startTime, String endTime, Integer pay){
        Student student = (Student) request.getSession().getAttribute("user");
        Page<WaterRecordDto> page;
        if (null == student){
            page = waterRecordService.pageWaterRecord(currentPage, pageSize, 0, 0, startTime, endTime, pay);
        }
        else{
            page = waterRecordService.pageWaterRecord(currentPage, pageSize, 0, student.getDormitoryId(), startTime, endTime, pay);
        }

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd");
    }

    @ResponseBody
    @RequestMapping(value = "/electricRecordSearch")
    public String electricRecordSearch(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                       HttpServletRequest request, String startTime, String endTime, Integer pay){
        Student student = (Student) request.getSession().getAttribute("user");
        Page<ElectricRecordDto> page;
        if (null == student){
            page = electricRecordService.pageElectricRecord(currentPage, pageSize, 0, 0 , startTime, endTime, pay);
        }
        else{
            page = electricRecordService.pageElectricRecord(currentPage, pageSize, 0, student.getDormitoryId(), startTime, endTime, pay);
        }

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd");
    }


    @ResponseBody
    @RequestMapping(value = "/updateStudentPhone")
    public String updateStudentPhone(Student student){
        Map map = new HashMap();
        if ( null == student){
            map.put("message", false);
        }
        else{
            if (studentService.updateStudentPhone(student)){
                map.put("message", true);
            }
            else{
                map.put("message", true);
            }
        }

        return JSON.toJSONString(map);

    }

    @ResponseBody
    @RequestMapping(value = "/getDormitory")
    public String getDormitory(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("user");
        Map map = new HashMap();
        if( null == student){
            map.put("message", false);
            map.put("error", 0);
        }
        else{
            if(student.getDormitoryId() == 0){
                map.put("message", false);
                map.put("error", 1);
            }
            else{
                Dormitory dormitory = dormitoryService.findDormitoryById(student.getDormitoryId());
                if(null == dormitory){
                    map.put("message", false);
                    map.put("error", 0);
                }
                else{
                    map.put("message", true);
                    map.put("dormitory", dormitory);
                }
            }


        }
        return JSON.toJSONString(map);
    }


}
