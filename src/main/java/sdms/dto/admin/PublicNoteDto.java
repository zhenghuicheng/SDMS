package sdms.dto.admin;


import java.util.Date;

/**
 * Created by cheng on 2017/1/22.
 */
public class PublicNoteDto {

    private Integer id;
    private String title;
    private Date publicTime;
    private String context;
    private Integer adminId;
    private Integer status;
    private String adminName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }
}
