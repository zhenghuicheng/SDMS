package sdms;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;
import sdms.service.admin.IBuildingService;
import sdms.service.admin.IGoodsRecordService;
import sdms.service.admin.ISanitationService;
import sdms.service.admin.IVisitRecordService;

import java.io.FileNotFoundException;

/**
 * Created by cheng on 2017/1/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class BuildingServiceTest {

    private static Logger logger = Logger.getLogger(BuildingServiceTest.class);

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private ISanitationService sanitationService;

    @Autowired
    private IGoodsRecordService goodsRecordService;

    @Autowired
    private IVisitRecordService visitRecordService;

    @Before
    public void before(){
        try{
            Log4jConfigurer.initLogging("classpath:config/log/log4j.properties");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

   @Test
    public void addBuilding(){
        /*Building building = new Building();
        building.setNumber(1);
        building.setFloor(6);
        building.setFloorNumber(20);
        building.setSex(0);
        building.setDepartmentId(16);
        building.setAdminId(3);
        buildingService.addBuilding(building);*/
    }


}
