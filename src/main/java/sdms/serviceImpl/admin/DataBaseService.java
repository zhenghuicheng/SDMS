package sdms.serviceImpl.admin;

import org.springframework.stereotype.Service;
import sdms.service.admin.IDataBaseService;
import sdms.util.GetProperty;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Runtime.getRuntime;

/**
 * Created by cheng on 2017/3/7.
 */
@Service
public class DataBaseService implements IDataBaseService{

    public String save(String path) throws IOException, InterruptedException {

        //注意路径这是包的写法，不是文件夹写法，还有不用写文件属性
        String userName = GetProperty.getPropertyByNameOne("config.ds.ds-jdbc", "jdbc.username");
        String password = GetProperty.getPropertyByNameOne("config.ds.ds-jdbc", "jdbc.password");
        String dataBase = GetProperty.getPropertyByNameOne("config.ds.ds-jdbc", "jdbc.dataname");


        //日期标志，用于生成文件名
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        String dataflag = df.format(date);

        //下载文件名
        String fileName = dataflag + ".sql";

        //下载路径名
        String downloadURL = path + "\\" + fileName;

        //备份语句
        StringBuilder command = new StringBuilder();
        command.append("cmd  /c " + path + "\\mysqldump -u ");
        command.append(userName);
        command.append(" -p");
        command.append(password + " " + "-h " + "127.0.0.1 " + "--opt " + dataBase + " > " + downloadURL);
        Process ls_proc = getRuntime().exec(command.toString());
        ls_proc.waitFor();
        return fileName;
    }

    /*
    在还原数据库的数据时，总是出现问题，后来发现调用的sql文件原因。还原的sql文件最好是同一个软件备份出来的
    不然很容易出问题，如我用了navicat软件导出的文件，在用dos界面运行cmd命令时就发现总是有问题，用了cmd命令
    导出的文件就不会出现这个问题
     */
    public int backup(String path, String fileName) throws IOException, InterruptedException {

        String userName = GetProperty.getPropertyByNameOne("config.ds.ds-jdbc", "jdbc.username");
        String password = GetProperty.getPropertyByNameOne("config.ds.ds-jdbc", "jdbc.password");
        String dataBase = GetProperty.getPropertyByNameOne("config.ds.ds-jdbc", "jdbc.dataname");
        String filePath = path + "\\" + fileName;

        StringBuilder createSql = new StringBuilder();
        StringBuilder restoreSql = new StringBuilder();

        //创建数据库
        createSql.append("cmd /c " + path + "\\mysqladmin -u " + userName);
        createSql.append(" -p" + password + " create " + dataBase);

        //还原数据
        restoreSql.append("cmd /c " + path + "\\mysql -u " + userName);
        restoreSql.append(" -p" + password + " " + dataBase + " < " + filePath);

        getRuntime().exec(createSql.toString());
        Process p = Runtime.getRuntime().exec(restoreSql.toString());
        int value = p.waitFor();

        return value;


    }
}
