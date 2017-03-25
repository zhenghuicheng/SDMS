package sdms;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;
import sdms.service.admin.IAdminLoginLogService;
import sdms.service.admin.IAdminService;
import sdms.service.admin.IStudentService;

import java.io.FileNotFoundException;

/**
 * Created by cheng on 2016/12/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class AadminServiceTest {
    private static Logger logger = Logger.getLogger(AadminServiceTest.class);

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAdminLoginLogService adminLoginLogService;

    @Autowired
    private IStudentService studentService;

    @Before
    public void before(){
        try{
            Log4jConfigurer.initLogging("classpath:config/log/log4j.properties");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

/*    @Test
    public void findAdmin(){

      *//* Admin admin = new Admin();
        admin.setPassword("123abc");
        admin.setName("zheng");
        admin.setJobNumber("0001");
        adminService.addAdmin(admin);*//**//**//**//*

        logger.info(adminService.findVerifyAdmin("0001","123ab"));*//*
    }*/

    /*@Test
    public void pageAdmin(){
        Page<Admin> page = adminService.pageAdmin(1, 10);
        logger.info(JSON.toJSONString(page));
    }*/

    /*@Test
    public void readExcel() throws IOException {
        File file = new File("F:\\test.xls");

        List<List<Object>> list = ReadExcel.readExcel(file);
        List<String> repeatList = studentService.findStudentVerifyExcel(list);
        if(null == repeatList){
            logger.info("读取文件数据失败或文件没有内容");
        }else if(0 == repeatList.size()){
            logger.info("文件数据没有重复");
        }
        else{
            logger.info(JSON.toJSONString(repeatList));
        }

    }*/

    /*@Test
    public void testRepeat(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        if(list.size() == new HashSet<>(list).size()){
            logger.info("You are right");
        }
        else{
            logger.info("You are false");
        }
    }*/

    @Test
    public void test(){
        Long count = studentService.countStudentByProfessionId(5);
        logger.info(count);
        logger.info("郑辉城");
    }


}
