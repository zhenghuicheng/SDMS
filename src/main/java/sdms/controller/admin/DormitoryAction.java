package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.DormitoryDto;
import sdms.dto.admin.DormitoryStatisticsDto;
import sdms.entity.admin.Dormitory;
import sdms.entity.admin.Student;
import sdms.service.admin.IDormitoryService;
import sdms.service.admin.IStudentService;
import sdms.util.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/1/31.
 */
@Controller
@RequestMapping(value = "/dormitory")
public class DormitoryAction {

    @Autowired
    private IDormitoryService dormitoryService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "/dormitory/index";
    }

    @RequestMapping(value = "/statistics")
    public String statistics(){
        return "/dormitory/statistics";
    }
    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer buildingId, Integer floor, Integer dormitoryId, String type, Integer studentNumber){
        Page<DormitoryDto> page = dormitoryService.pageDormitory(currentPage, pageSize, buildingId, floor, dormitoryId, type, studentNumber);
        return JSON.toJSONString(page);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String list(Integer buildingId){
        List<Dormitory> list = dormitoryService.listDormitoryByBuildingId(buildingId);
        return JSON.toJSONString(list);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String updateDormitory(Dormitory dormitory){
        Map map = new HashMap<>();
        if(null == dormitory){
            map.put("message", false);
        }
        else{
            if(dormitoryService.updateDormitory(dormitory)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String deleteDormitory(Integer[] id){
        Map map = new HashMap();
        if(null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            if(dormitoryService.deleteDormitory(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/student")
    public String getStudent(Integer id){
        List<Student> list = studentService.findStudentByDormitoryId(id);

        return JSON.toJSONString(list);

    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String addDormitory(Dormitory dormitory){
        Map map = new HashMap();
        if(null == dormitory){
            map.put("message", false);
        }
        else{
            if(dormitoryService.addDormitory(dormitory)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public String getDormitory(Integer id){

        Dormitory dormitory;
        if(null == id){
            dormitory = new Dormitory();
        }
        else{
            dormitory = dormitoryService.findDormitoryById(id);
        }

        return JSON.toJSONString(dormitory);


    }

    @ResponseBody
    @RequestMapping(value = "/validatorNumber")
    public String validatorNumber(Integer buildingId, Integer number){
        Map map = new HashMap();
        Long count = dormitoryService.countDormitoryByBuildingidAndNumber(buildingId, number);
        if(0 == count){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/listFloor")
    public String listFloor(Integer buildingId, Integer floor){
        List<Dormitory> list;
        list = dormitoryService.listDormitoryByBuildingIdAndFloor(buildingId, floor);
        if(null == list){
            list = new ArrayList<>();
        }

        return JSON.toJSONString(list);
    }

    @ResponseBody
    @RequestMapping(value = "/statistics/search")
    public String statisticsDormitory(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                                      Integer buildingId, String type, Integer studentNumber){
        Page<DormitoryStatisticsDto> page = dormitoryService.statisticsDormitory(currentPage, pageSize, buildingId, type, studentNumber);

        return JSON.toJSONString(page);
    }









}
