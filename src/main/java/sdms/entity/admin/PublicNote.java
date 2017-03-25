package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2017/1/22.
 */
@Entity
@Table(name = "public_note")
public class PublicNote {

    private Integer id;
    private String title;
    private String publicTime;
    private String context;
    private Integer adminId;
    private Integer status;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "public_time")
    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    @Column(name = "context")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Column(name = "adminId")
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
