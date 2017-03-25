package sdms.dto.admin;

import java.util.Date;

/**
 * Created by cheng on 2017/2/4.
 */
public class RepairsRecordDto {

    private Integer id;
    private Integer dormitoryId;
    private Integer studentId;
    private Integer status;
    private Date repairsTime;
    private Integer isRepairs;
    private String context;
    private String name;
    private Integer buildingNumber;
    private Integer dormitoryNumber;
    private String number;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRepairsTime() {
        return repairsTime;
    }

    public void setRepairsTime(Date repairsTime) {
        this.repairsTime = repairsTime;
    }

    public Integer getIsRepairs() {
        return isRepairs;
    }

    public void setIsRepairs(Integer isRepairs) {
        this.isRepairs = isRepairs;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
