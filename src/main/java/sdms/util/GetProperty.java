package sdms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 获取web配置文件属性
 * 代码来自http://blog.csdn.net/u013456370/article/details/51151107
 * 注意路径这是包的写法，不是文件夹写法，还有不用写文件属性
 * Created by cheng on 2017/3/7.
 */
public class GetProperty {


    /*
    通过java.util.ResourceBundle读取资源属性文件
     */
    public static String getPropertyByNameOne(String path, String name){
        String result = "";


        result = ResourceBundle.getBundle(path).getString(name);

        return result;
    }


    /*
    通过类加载目录getClassLoader()加载属性文件
     */
    public static String getPropertyByNameTwo(String path, String name){
        String result = "";

        // InputStream in =
        // this.getClass().getClassLoader().getResourceAsStream("mailServer.properties");

        // 注：Object.class.getResourceAsStream在action中调用报错，在普通java工程中可用
        // InputStream in =
        // Object.class.getResourceAsStream("/mailServer.properties");
        InputStream in= GetProperty.class.getClassLoader().getResourceAsStream(path);
        Properties prop = new Properties();



        try{
            prop.load(in);
            result = prop.getProperty(name).trim();
        }
        catch (IOException e){
            System.out.println("读取配置文件出错");
        }

        return result;

    }




}
