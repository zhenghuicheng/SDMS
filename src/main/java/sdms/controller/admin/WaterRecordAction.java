package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.WaterRecordDto;
import sdms.entity.admin.WaterRecord;
import sdms.service.admin.IWaterRecordService;
import sdms.util.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/1/31.
 */
@Controller
@RequestMapping(value = "/waterRecord")
public class WaterRecordAction {

    @Autowired
    private IWaterRecordService waterRecordService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "waterRecord/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(WaterRecord waterRecord){
        Map map = new HashMap<>();
        if( null == waterRecord){
            map.put("message", false);
        }
        else{
            try{
                if(waterRecordService.addWaterRecord(waterRecord)){
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
        Page<WaterRecordDto> page = waterRecordService.pageWaterRecord(1, 10, buildingId, dormitoryId, startTime, endTime, pay);
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
            if(waterRecordService.deleteWaterRecord(id)){
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
    public String update(WaterRecord waterRecord){
        Map map = new HashMap();
        if( null == waterRecord){
            map.put("message",false);
        }
        else{
            try{
                if(waterRecordService.updateWaterRecord(waterRecord)){
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
            if(waterRecordService.updateWaterRecordPay(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

}
