package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.BuildingDto;
import sdms.entity.admin.Building;
import sdms.service.admin.IBuildingService;
import sdms.service.admin.IDormitoryService;
import sdms.util.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/1/30.
 */
@Controller
@RequestMapping(value = "/building")
public class BuildingAction {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IDormitoryService dormitoryService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "/building/index";
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer departmentId, Integer adminId){
        Page<BuildingDto> page = buildingService.pageBuilding(currentPage, pageSize,
                departmentId, adminId);

        return JSON.toJSONString(page);
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(Building building){
        Map map = new HashMap<>();

        if( null == building){
            map.put("message",false);
        }
        else{
            if( buildingService.addBuilding(building)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }

        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String list(){
        return JSON.toJSONString(buildingService.listBuilding());
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(Building building){
        Map map = new HashMap();
        if(null == building){
            map.put("message", false);
        }
        else{
            if(buildingService.updateBuilding(building)){
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
    public String delete(Integer[] id){
        Map map = new HashMap();
        boolean status = true;
        List<Integer> buildingList = new ArrayList<>();
        if(null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            for(int i = 0; i < id.length; i++){
                if(dormitoryService.countDormitoryByBuildingId(id[i]) != 0){
                    buildingList.add(buildingService.findBuildingById(id[i]).getNumber());
                    status = false;
                }
            }
            if(status == true){
                buildingService.deleteBuilding(id);
                map.put("message", true);
            }
            else{
                map.put("message", false);
                map.put("number", buildingList);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/validator")
    public String validator(Integer number){
        Map map = new HashMap();
        Long count = buildingService.countBuildingByNumber(number);
        if(count == 0){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }
}
