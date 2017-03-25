package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2016/12/30.
 */
@Entity
@Table(name = "admin_login_log")
public class AdminLoginLog {

    private Integer id;
    private Integer adminId;
    private String loginTime;
    private String ip;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "login_time")
    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "admin_Id")
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
