package sdms;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;
import sdms.service.admin.*;

import java.io.FileNotFoundException;

/**
 * Created by cheng on 2017/1/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class DepartmentServiceTest {

    private static Logger logger = Logger.getLogger(AadminServiceTest.class);

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IProfessionService professionService;

    @Autowired
    private IPublicNoteService publicNoteService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IDormitoryService dormitoryService;

    @Autowired
    private IDataBaseService dataBaseService;

    @Before
    public void before(){
        try{
            Log4jConfigurer.initLogging("classpath:config/log/log4j.properties");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /*@Test
    public void addDepartment(){
        Department department = new Department();
        department.setName("数学与信息学院");
        department.setNumber("00001");
        department.setBuilding("数学与信息院楼");
        department.setDescription("数学与信息学院办学优势突出，学科综合实力强，其前身为理学院和信息学院。");
        department.setPhone("85280320-514");
        department.setStatus(1);
        departmentService.addDepartment(department);
    }*/

    /*@Test
    public void TestStudent(){
        String path = "F:\\JavaProject\\JavaSE\\SDMS\\src\\main\\webapp\\static\\backupFile";
        String fileName = "1488941171306.sql";
        String message;

        try {
            dataBaseService.backup(path, fileName);
            message = "北方有佳人，绝世而独立";
        } catch (IOException e) {
            message = "一顾倾人城，一顾倾人国";
        }

        logger.info(message);
    }*/


}
