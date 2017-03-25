package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.entity.admin.Department;
import sdms.service.admin.IDepartmentService;
import sdms.service.admin.IProfessionService;
import sdms.util.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/1/14.
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentAction {

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IProfessionService professionService;

    @RequestMapping(value = {"","/index"})
    public String index(){
        return "/department/index";
    }

    @ResponseBody
    @RequestMapping(value = {"/add"})
    public String add(Department department){
        Map map = new HashMap<>();
        if (null != department){
            department.setStatus(1);
            if (departmentService.addDepartment(department)){
                map.put("message","true");
            }
            else {
                map.put("message", "false");
            }
        }
        else{
            map.put("message", "false");
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize, Integer number, String name){
        Page<Department> page = departmentService.pageDepartment(currentPage, pageSize, number, name);

        return JSON.toJSONString(page);
    }

    @ResponseBody
    @RequestMapping(value = "/edit")
    public String edit(Department department){
        Map map = new HashMap();
        if(null != department){
            department.setStatus(1);
            if (departmentService.updateDepartment(department)){
                map.put("message",true);
            }
            else{
                map.put("message",false);
            }
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
        if (departmentService.deleteDepartment(id)){
            map.put("message",true);
        }
        else{
            map.put("message",false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String listDepartment(){
        return JSON.toJSONString(departmentService.listDepartment());
    }

    @ResponseBody
    @RequestMapping(value = "/validateName")
    public String validateName(String name){
        Map map = new HashMap();
        Long count = departmentService.countDepartmentByName(name);
        if(count == 0){
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
        Long count = departmentService.countDepartmentByNumber(number);
        if(count == 0){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    /*
    验证该学院下现在有没有专业
     */
    @ResponseBody
    @RequestMapping(value = "/getProfession")
    public String getProfession(Integer[] id){
        Map map = new HashMap();
        Boolean status = true;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < id.length; i++){
            if(professionService.countProfessionByDepartmentId(id[i]) != 0){
                    status = false;
                    Department department = departmentService.findDepartmentById(id[i]);
                    list.add(department.getName());

            }
        }
        if(status == true){
            map.put("message", true);
        }
        else{
            map.put("message", false);
            map.put("list", list);
        }
        return  JSON.toJSONString(map);
    }


}
