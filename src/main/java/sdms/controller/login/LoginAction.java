package sdms.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import scau.zxck.base.utils.DateUtil;
import scau.zxck.base.utils.RandomUtil;
import sdms.entity.admin.Admin;
import sdms.entity.admin.AdminLoginLog;
import sdms.entity.admin.Student;
import sdms.service.admin.IAdminLoginLogService;
import sdms.service.admin.IAdminService;
import sdms.service.admin.IStudentService;
import sdms.util.CommonsUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * Created by cheng on 2016/12/28.
 */
@Controller
@RequestMapping(value = {"/login",""})
public class LoginAction {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAdminLoginLogService adminLoginLogService;

    @Autowired
    private IStudentService  studentService;

    @RequestMapping(value = {"/index",""})
    public String index(){
        return "login/login";
    }


    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, String random, String name, String password, String role){

        if (request.getSession().getAttribute("random") == null){
            redirectAttributes.addFlashAttribute("message","验证码错误");
            return "redirect:index";
        }
        String originalRandom = (String) request.getSession().getAttribute("random");
        if (originalRandom.equalsIgnoreCase(random)){
            password = CommonsUtil.enPassword(password);
            UsernamePasswordToken token = new UsernamePasswordToken(name +  "," + role, password);


            try{
                SecurityUtils.getSubject().login(token);

                if(role.equals("1")){
                    Admin admin = adminService.findByJobName(name);
                    request.getSession(true).setAttribute("admin",admin);

                    //登录日志
                    AdminLoginLog adminLoginLog = new AdminLoginLog();
                    adminLoginLog.setIp(CommonsUtil.getIp(request));
                    adminLoginLog.setAdminId(admin.getId());
                    adminLoginLog.setLoginTime(DateUtil.getSimpleDate(new Date()));
                    adminLoginLogService.addLoginLog(adminLoginLog);
                    return "redirect:/admin/test";
                }
               else{
                    Student student = studentService.findStudentByNumber(name);
                    request.getSession(true).setAttribute("user", student);
                    return "redirect:/user/index";

                }


            }
            catch (AuthenticationException e){
                redirectAttributes.addFlashAttribute("message","用户名或密码错误");
                return "redirect:index";
            }

        }
        else{
            redirectAttributes.addFlashAttribute("message","验证码错误");
            return "redirect:index";
        }

    }


    @RequestMapping(value = "/verify")
    public void verificationCode(HttpServletRequest request, HttpServletResponse response){

        int width = 80;
        int height = 30;
        String random = RandomUtil.getRandom(4);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();

        request.getSession(true).setAttribute("random", random);
        response.setContentType("image/jpeg");
        RandomUtil.drawRandomPicture(g, width, height, random);

        try {
            ImageIO.write(bi,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login/login";
    }
}
