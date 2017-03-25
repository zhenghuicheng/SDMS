package sdms;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;
import sdms.service.admin.IDormitoryService;
import sdms.service.admin.IRepairsRecordService;
import sdms.service.admin.ISendWaterRecordService;
import sdms.service.admin.IWaterRecordService;
import sdms.util.BackupMysql;

import java.io.FileNotFoundException;

/**
 * Created by cheng on 2017/1/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class DormitoryServiceTest {

    private static Logger logger = Logger.getLogger(AadminServiceTest.class);

    @Autowired
    private IDormitoryService dormitoryService;

    @Autowired
    private IWaterRecordService waterRecordService;

    @Autowired
    private IRepairsRecordService repairsRecordService;

    @Autowired
    private ISendWaterRecordService sendWaterRecordService;

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
    public void addDormitory(){
        Dormitory dormitory = new Dormitory();
        dormitory.setFloor(1);
        dormitory.setBedNumber(4);
        dormitory.setNumber(101);
        dormitory.setBuildingId(9);
        dormitory.setStudentNumber(0);

        dormitoryService.addDormitory(dormitory);
    }*/

    /*@Test
   public void pageWaterRecordPage(){
        Page<WaterRecordDto> list = waterRecordService.pageWaterRecord(1, 10);
        logger.info(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd"));
    }*/

    @Test
    public void addRepairsRecord(){
      /* RepairsRecord repairsRecord = new RepairsRecord();
        repairsRecord.setStudentId(1);
        repairsRecord.setContext("宿舍四支灯管都查杯具了，肿么办");
        repairsRecordService.addRepairsRecord(repairsRecord, 1);
       //logger.info(JSON.toJSONString(repairsRecordService.pageRepairRecord(1, 10)));
        SendWaterRecord sendWaterRecord = new SendWaterRecord();
        sendWaterRecord.setNumber(2);

        sendWaterRecordService.addSendWaterRecord(sendWaterRecord, 1);

        logger.info(JSON.toJSONString(sendWaterRecordService.pageSendWaterRecord(1, 10)));*/
        String serverUrl="127.0.0.1";

        String userName = "root";

        String pwd = "123abc";

        BackupMysql backup = new BackupMysql(serverUrl,userName, pwd);

        try {
            backup.backup("e:\\", "sdms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
