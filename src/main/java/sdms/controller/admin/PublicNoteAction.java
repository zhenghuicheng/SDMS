package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdms.entity.admin.Admin;
import sdms.entity.admin.PublicNote;
import sdms.service.admin.IPublicNoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/1/22.
 */
@Controller
@RequestMapping(value = "/publicNote")
public class PublicNoteAction {

    @Autowired
    private IPublicNoteService publicNoteService;

    @RequestMapping(value = {"/index",""})
    public String index(){
       return "/publicNote/index";
    }

    @RequestMapping(value = "/first")
    public String first(){
        return "/publicNote/first";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String addPublicNote(HttpServletRequest request, PublicNote publicNote){
        Map map = new HashMap();

        if( null == publicNote){
            map.put("message",false);
        }
        else{
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            publicNote.setAdminId(admin.getId());
            if(publicNoteService.addPublicNote(publicNote)){
                map.put("message",true);
            }
            else{
                map.put("message",false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize, String title, String startTime, String endTime){
        return JSON.toJSONStringWithDateFormat(publicNoteService.pagePublicNote(currentPage, pageSize, title, startTime, endTime), "yyyy-MM-dd HH:mm:ss");
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer[] id ){

        Map map = new HashMap();

        if( id == null || id.length == 0){
            map.put("message",false);
        }
        else{
            if( publicNoteService.deletePublicNote(id)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/edit")
    public String edit(PublicNote publicNote){
        Map map = new HashMap();
        if (null == publicNote){
            map.put("message", false);
        }
        else{
            if (publicNoteService.updatePublicNote(publicNote)){
                map.put("message", true);
            }
            else{
                map.put("message", false);
            }
        }

        return JSON.toJSONString(map);
    }

}
