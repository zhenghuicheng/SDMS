package sdms.dto.admin;

import java.math.BigDecimal;

/**
 * Created by cheng on 2017/3/6.
 */
public class DormitoryStatisticsDto {

    private Integer id;
    private Integer buildingId;
    private Integer floor;
    private BigDecimal bedNumber;
    private BigDecimal studentNumber;
    private Integer buildingNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public BigDecimal getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(BigDecimal bedNumber) {
        this.bedNumber = bedNumber;
    }

    public BigDecimal getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(BigDecimal studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
