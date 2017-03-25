package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.ElectricRecordDto;
import sdms.entity.admin.ElectricRecord;
import sdms.service.admin.IElectricRecordService;
import sdms.util.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/1.
 */
@Controller
@RequestMapping(value = "/electricRecord")
public class ElectricRecordAction {

    @Autowired
    private IElectricRecordService electricRecordService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "electricRecord/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(ElectricRecord electricRecord){
        Map map = new HashMap<>();
        if( null == electricRecord){
            map.put("message", false);
        }
        else{
            try{
                if(electricRecordService.addElectricRecord(electricRecord)){
                    map.put("message", true);
                }
                else{
                    map.put("message", false);
                }
            }
            catch (Exception e){
                map.put("message",false);
            }

        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer buildingId, Integer dormitoryId, String startTime, String endTime, Integer pay){
        Page<ElectricRecordDto> page;

        try{
            page = electricRecordService.pageElectricRecord(currentPage, pageSize, buildingId, dormitoryId, startTime, endTime, pay);
        }
        catch (Exception e){
            page = new Page<>();
        }
        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd");
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id){
        Map map = new HashMap();
        if(null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            if(electricRecordService.deleteElectricRecord(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(ElectricRecord electricRecord){
        Map map = new HashMap();
        if( null == electricRecord){
            map.put("message",false);
        }
        else{
            try{
                if(electricRecordService.updateElectricRecord(electricRecord)){
                    map.put("message",true);
                }
                else{
                    map.put("message",false);
                }
            }
            catch (Exception e){
                map.put("message",false);
            }

        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/pay")
    public String pay(Integer id){
        Map map = new HashMap();
        if(null == id){
            map.put("message", false);
        }
        else{
            if(electricRecordService.updatePay(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }
        return JSON.toJSONString(map);
    }
}
