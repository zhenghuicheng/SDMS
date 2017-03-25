package sdms.dto.admin;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheng on 2017/2/1.
 */
public class WaterRecordDto {

    private Integer id;
    private Integer dormitoryId;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private BigDecimal waterRate;
    private Integer pay;
    private Integer buildingNumber;
    private Integer dormitoryNumber;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public BigDecimal getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(BigDecimal waterRate) {
        this.waterRate = waterRate;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
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
