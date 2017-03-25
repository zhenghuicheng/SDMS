package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.SanitationDto;
import sdms.entity.admin.Sanitation;
import sdms.service.admin.ISanitationService;
import sdms.util.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/2/2.
 */
@Controller
@RequestMapping(value = "/sanitation")
public class SanitationAction {

    @Autowired
    private ISanitationService sanitationService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/sanitation/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(Sanitation sanitation){
        Map map = new HashMap<>();
        if( null == sanitation){
            map.put("message",false);
        }
        else{
            try{
                if(sanitationService.addSanitation(sanitation)){
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
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id){

        Map map = new HashMap();
        if(null == id || id.length ==0){
            map.put("message",false);
        }
        else{
            try{
                if(sanitationService.deleteSanitation(id)){
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
    @RequestMapping(value = "/update")
    public String update(Sanitation sanitation){
        Map map = new HashMap();
        if(null == sanitation){
            map.put("message",false);
        }
        else{
            try{
               if(sanitationService.updateSanitation(sanitation)){
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
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer buildingId, Integer dormitoryId,
                         String startTime, String endTime, Integer degree){
        Page<SanitationDto> page = sanitationService.pageSanitation(currentPage, pageSize,
                buildingId, dormitoryId, startTime, endTime, degree);
        /*try{

            page = sanitationService.pageSanitation(currentPage, pageSize,
                    buildingId, dormitoryId, startTime, endTime, degree);
        }
        catch (Exception e){
            page = new Page<>();
        }*/

        return JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd");
    }



}
