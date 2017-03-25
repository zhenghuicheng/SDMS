package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2017/1/31.
 */
@Entity
@Table(name = "water_record")
public class WaterRecord {

    private Integer id;
    private Integer dormitoryId;
    private Integer status;
    private String startTime;
    private String endTime;
    private Double waterRate;
    private Integer pay;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "dormitory_id")
    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }


    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name = "water_rate")
    public Double getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Double waterRate) {
        this.waterRate = waterRate;
    }

    @Column(name = "pay")
    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }
}
