package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.GoodsRecordDto;
import sdms.entity.admin.GoodsRecord;
import sdms.service.admin.IGoodsRecordService;
import sdms.util.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/2.
 */
@Controller
@RequestMapping(value = "/goodsRecord")
public class GoodsRecordAction {

    @Autowired
    private IGoodsRecordService goodsRecordService;

    @RequestMapping(value = "/index")
    public String index(){
        return "goodsRecord/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(GoodsRecord goodsRecord){
        Map map = new HashMap<>();

        if( null == goodsRecord){
            map.put("message",false);
        }
        else{
            try{
                if(goodsRecordService.addGoodsRecord(goodsRecord)){
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

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer buildingId, String name, String startTime, String endTime){
        Page<GoodsRecordDto> page;
        try{
            page = goodsRecordService.pageGoodsRecord(currentPage, pageSize,
                    buildingId, name, startTime, endTime);
        }
        catch (Exception e){
            page = new Page<>();
        }
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
            try{
                if(goodsRecordService.deleteGoodsRecord(id)){
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

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(GoodsRecord goodsRecord){
        Map map = new HashMap();
        if( null == goodsRecord){
            map.put("message", false);
        }
        else{
            if(goodsRecordService.updateGoodsRecord(goodsRecord)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }


}
