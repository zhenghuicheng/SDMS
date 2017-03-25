package sdms.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sdms.service.admin.IDataBaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/3/7.
 */
@Controller
@RequestMapping(value = "/dataBase")
public class DataBaseAction {

    @Autowired
    private IDataBaseService dataBaseService;

    @RequestMapping(value = "/index")
    public String index(){
        return "dataBase/index";
    }

    @RequestMapping(value = "/save")
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        String path = request.getServletContext().getRealPath("/static/saveFile");
        String fileName = dataBaseService.save(path);
        File file = new File(path + "\\" + fileName);


        response.setContentType("application/x-msdownload");
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition", "attachment;filename="
                + fileName);

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream buff = new BufferedInputStream(fis);
        // 相当于我们的缓存
        byte[] b = new byte[1024];
        // 该值用于计算当前实际下载了多少字节
        long k = 0;
        // 从response对象中得到输出流,准备下载
        OutputStream myout = response.getOutputStream();
        // 开始循环下载
        while (k < file.length()) {
            int j = buff.read(b, 0, 1024);
            k += j;
            // 将b中的数据写到客户端的内存
            myout.write(b, 0, j);
        }
        myout.flush();

    }

    @ResponseBody
    @RequestMapping(value = "/backup")
    public String backup(HttpServletRequest request, @RequestParam("backupFile")CommonsMultipartFile file){

        Map map = new HashMap();

        String path = request.getServletContext().getRealPath("/static/backupFile");
        String extension = file.getOriginalFilename().lastIndexOf(".") == -1 ? "" : file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = System.currentTimeMillis() + "." + extension;

        File targetFile = new File(path, fileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try{
            file.transferTo(targetFile);
            int  value = dataBaseService.backup(path, fileName);
            if(value != 0){
                map.put("message", false);
            }
            else{
                map.put("message", true);
            }

        }
        catch (Exception e){
            map.put("message", false);
        }
        return JSON.toJSONString(map);

    }


}
