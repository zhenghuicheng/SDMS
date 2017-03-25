package sdms.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cheng on 2017/3/7.
 */
public class BackupMysql {

    /** 访问MySQL数据库服务器所在的url */
    private String serverUrl;
    /** 访问MySQL数据库的用户名 */
    private String username;
    /** 访问MySQL数据库的密码 */
    private String password;


    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BackupMysql(String serverUrl,String username, String password) {
        super();
        this.serverUrl=serverUrl;
        this.username = username;
        this.password = password;
    }

    public String backup(String backupPath, String dbName) throws IOException {

        String backupFile = backupPath+ dbName+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".sql";

        String mysql = "mysqldump "+"--host="+serverUrl+" --user=" + username + " --password="+ password + " --opt " + dbName + "> " + backupFile;

        java.lang.Runtime.getRuntime().exec("cmd /c " + mysql);

        System.out.println("备份成功!");

        return backupFile;

    }

    public void restore(String restoreFile, String dbName) throws Exception {

        String mysql = "mysql "+"-h"+serverUrl+" -u" + username + " -p"+ password + " " + dbName + " < " + restoreFile;

        System.out.println(mysql);

        java.lang.Runtime.getRuntime().exec("cmd /c " + mysql);

        System.out.println("还原成功!");
    }
}
