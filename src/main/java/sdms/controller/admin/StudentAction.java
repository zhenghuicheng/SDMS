package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sdms.dto.admin.StudentDto;
import sdms.entity.admin.Dormitory;
import sdms.entity.admin.Student;
import sdms.service.admin.IDormitoryService;
import sdms.service.admin.IStudentService;
import sdms.util.Page;
import sdms.util.ReadExcel;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/1/19.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentAction {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IDormitoryService dormitoryService;

    @RequestMapping(value = {"/index",""})
    public String index(){
       return "/student/index";
    }

    @ResponseBody
    @RequestMapping(value = {"/add"})
    public String add(Student student){
        Map map = new HashMap<>();

        if( null == student){
            map.put("message", false);
        }
        else{
            if (studentService.addStudent(student)){
                map.put("message",true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = {"/search"})
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize, Integer departmentId, Integer grade,
                         Integer professionId, Integer classNumber, Integer sex, Integer dormitoryId, String name, String number){
        Page<StudentDto> page = studentService.pageStudent(currentPage, pageSize,departmentId, grade, professionId, classNumber, sex, dormitoryId, name, number);
        return JSON.toJSONString(page);
    }

    @ResponseBody
    @RequestMapping(value = {"/resetPassword"})
    public String resetPassword(Integer id){
        Map map = new HashMap();

        if( null == id || id == 0){
            map.put("message", false);
        }
        else{
            if(studentService.updatePasswordByAdmin(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = {"/quit"})
    public String quitDormitory(Integer id){
        Map map = new HashMap();
        if( null == id){
            map.put("message", false);
        }
        else{
            if(studentService.updateDormitory(id)){
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
    public String updateStudent(Student student){
        Map map = new HashMap();

        if( null == student){
            map.put("message", false);
        }
        else{
            try{
                if(studentService.updateStudent(student)){
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
    @RequestMapping(value = "/delete")
    public String deleteStudent(Integer[] id){
        Map map = new HashMap();
        if(null == id || id.length == 0){
            map.put("message", false);
        }
        else{
            if(studentService.deleteStudent(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/assign")
    public String assignDormitory(Integer studentId, Integer dormitoryId){
        Map map = new HashMap();
        if(null == studentId || dormitoryId == null){
            map.put("message", false);
        }
        else{
            Integer result = studentService.updateStudentDormitory(studentId, dormitoryId);

            if(result == 2){
                map.put("message", true);
            }
            else if(result == 1){
                map.put("message", false);
            }

        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/validateNumber")
    public String validateNumber(String number){
        Map map = new HashMap();
        Long count = studentService.countStudentByNumber(number);
        if(0 == count){
            map.put("valid", true);
        }
        else{
            map.put("valid", false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/uploading")
    public String uploading(HttpServletRequest request, @RequestParam("studentFile")CommonsMultipartFile file, Integer professionId){
        Map map = new HashMap();

        String path = request.getServletContext().getRealPath("/static/excelFile");
        String extension = file.getOriginalFilename().lastIndexOf(".") == -1 ? "" : file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = System.currentTimeMillis() + "." + extension;

        File targetFile = new File(path, fileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        try{
            file.transferTo(targetFile);
            List<List<Object>> originalList = ReadExcel.readExcel(targetFile);
            List<String> repeatExcelList = studentService.findStudentVerifyExcel(originalList);

            if(null == repeatExcelList){
                map.put("message", false);
                map.put("reason", "文件读取数据失败");
            }else if(repeatExcelList.size() > 0){
                map.put("message", false);
                map.put("firstList", repeatExcelList);
            }
            else{
                List<String> repeatDataBaseList = studentService.findStudentVerityExcelDataBase(originalList);
                if(repeatDataBaseList.size() > 0){
                    map.put("message", false);
                    map.put("twoList", repeatDataBaseList);
                }
                else if(studentService.addStudentByExcel(originalList, professionId)){
                    map.put("message", true);
                }
                else{
                    map.put("message", false);
                    map.put("reason", "文件中数据格式错误");
                }
            }

        } catch (IOException e) {
            map.put("message", false);
            map.put("reason", "文件读取数据失败");
        }
        catch(NullPointerException e){
            map.put("message", false);
            map.put("reason", "文件中数据格式错误");
        }


        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/IntelligentAllot")
    public String intelligentAllot(Integer[] id){
        Map map = new HashMap();
        int result = studentService.updateIntelligentDistribution(id);
        if(result == 5){
            map.put("message", true);
        }
        else{
            map.put("message", false);
            map.put("result", result);
        }

        return JSON.toJSONString(map);

    }

    @ResponseBody
    @RequestMapping(value = "/getDormitory")
    public String getDormitory(Integer id){
        Dormitory dormitory = dormitoryService.findDormitoryById(id);
        if(null == dormitory){
            dormitory = new Dormitory();
        }

        return JSON.toJSONString(dormitory);
    }




}
