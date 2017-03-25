package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.VisitRecordDto;
import sdms.entity.admin.VisitRecord;
import sdms.service.admin.IVisitRecordService;
import sdms.util.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/3.
 */
@Controller
@RequestMapping(value = "/visitRecord")
public class VisitRecordAction {

    @Autowired
    private IVisitRecordService visitRecordService;

    @RequestMapping(value = "/index")
    public String index(){
        return "visitRecord/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(VisitRecord visitRecord){
        Map map = new HashMap<>();
        if( null == visitRecord){
            map.put("message", false);
        }
        else{
            try{
                if(visitRecordService.addVisitRecord(visitRecord)){
                    map.put("message", true);
                }
                else{
                    map.put("message", false);
                }
            }
            catch (Exception E){
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id){
        Map map = new HashMap();
        if( null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            try{
                if(visitRecordService.deleteVisitRecord(id)){
                    map.put("message", true);
                }
                else{
                    map.put("message", false);
                }
            }
            catch(Exception e){
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
                         String visitor, String visitedName){
        Page<VisitRecordDto> page;
        try{
            page = visitRecordService.pageVisitRecord(currentPage, pageSize,
                    buildingId, dormitoryId, startTime, endTime, visitor, visitedName);
        }
        catch (Exception e){
            page = new Page<>();
        }

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss");
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(VisitRecord visitRecord){
        Map map = new HashMap();
        if( null == visitRecord){
            map.put("message", false);
        }
        else{
            try{
                if(visitRecordService.updateVisitRecord(visitRecord)){
                    map.put("message", true);
                }
                else{
                    map.put("message", false);
                }
            }
            catch (Exception e){
                map.put("message", false);
            }

        }

        return JSON.toJSONString(map);
    }
}
