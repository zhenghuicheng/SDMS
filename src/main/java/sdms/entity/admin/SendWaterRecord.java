package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2017/2/5.
 */
@Entity
@Table(name = "send_water_record")
public class SendWaterRecord {

    private Integer id;
    private Integer dormitoryId;
    private Integer studentId;
    private Integer status;
    private String time;
    private Integer isSend;
    private String name;
    private Integer number;

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

    @Column(name = "student_id")
    public Integer getStudentId() {
        return studentId;
    }


    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "is_send")
    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
