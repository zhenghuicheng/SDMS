package sdms.dto.admin;

import java.util.Date;

/**
 * Created by cheng on 2017/2/2.
 */
public class SanitationDto {

    private Integer id;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Integer dormitoryId;
    //0代表优，1表示良，2表示中，3表示差
    private Integer degree;
    private Integer buildingNumber;
    private Integer dormitoryNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getDormitoryNumber() {
        return dormitoryNumber;
    }

    public void setDormitoryNumber(Integer dormitoryNumber) {
        this.dormitoryNumber = dormitoryNumber;
    }
}
