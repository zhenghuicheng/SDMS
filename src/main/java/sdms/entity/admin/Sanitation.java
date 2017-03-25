package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2017/2/2.
 */
@Entity
@Table(name = "sanitation")
public class Sanitation {

    private Integer id;
    private String startTime;
    private String endTime;
    private Integer status;
    private Integer dormitoryId;
    //1代表优，2表示良，3表示中，4表示差
    private Integer degree;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "dormitory_id")
    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }






    @Column(name = "degree")
    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }
}
