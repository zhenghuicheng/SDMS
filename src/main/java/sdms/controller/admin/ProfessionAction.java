package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.dto.admin.ProfessionDto;
import sdms.entity.admin.Profession;
import sdms.service.admin.IProfessionService;
import sdms.service.admin.IStudentService;
import sdms.util.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/1/17.
 */
@Controller
@RequestMapping(value = "/profession")
public class ProfessionAction {

    @Autowired
    private IProfessionService professionService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "/profession/index";
    }


    @ResponseBody
    @RequestMapping(value = {"/search"})
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         Integer departmentId, String name){
        Page<ProfessionDto> page = professionService.pageProfession(currentPage, pageSize, departmentId, name);

        return JSON.toJSONString(page);
    }

    @ResponseBody
    @RequestMapping(value = {"/add"})
    public String add(Profession profession){
        Map map = new HashMap<>();

        if( null != profession){

            try{
                professionService.addProfession(profession);
                map.put("message", true);
            }
            catch (Exception e){
                map.put("message", false);
            }
        }
        else{
            map.put("message", true);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/edit")
    public String edit(Profession profession){
        Map map = new HashMap();

        if( professionService.updateProfession(profession)){
            map.put("message",true);
        }
        else{
            map.put("message",false);
        }

        return JSON.toJSONString(map);

    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id){
        Map map = new HashMap();

        if (null == id || id.length == 0){
            map.put("message", true);
        }
        else{
            if (professionService.deleteProfession(id)){
                map.put("message",true);
            }
            else{
                map.put("message",false);
            }
        }

        return JSON.toJSONString(map);

    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String listByDepartmentId(Integer departmentId){

        Map map = new HashMap();
        if(null == departmentId || departmentId == 0){
            map.put("message",false);
        }
        else{
            try{
                List<Profession> list = professionService.listByDepartment(departmentId);
                map.put("message",true);
                map.put("result", list);
            }
            catch(Exception E){
                map.put("message",false);
            }

        }
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/validateName")
    public String validateName(String name){
        Map map = new HashMap();
        Long count = professionService.countProfessionByName(name);

        if(0 == count){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/validateNumber")
    public String validateNumber(String number){
        Map map = new HashMap();
        Long count = professionService.countProfessionByNumber(number);
        if(0 == count){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/countStudent")
    public String countStudent(Integer[] id){
        Map map = new HashMap();
        Boolean status = true;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < id.length; i++){
            if(studentService.countStudentByProfessionId(id[i]) != 0){
                status = false;
                Profession profession = professionService.findProfessionById(id[i]);
                list.add(profession.getName());
            }
        }
        if(status == false){
            map.put("message", false);
            map.put("list", list);
        }
        else{
            map.put("message", true);
        }

        return JSON.toJSONString(map);
    }
}
