package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2017/2/4.
 */
@Entity
@Table(name = "repairs_record")
public class RepairsRecord {

    private Integer id;
    private Integer dormitoryId;
    private Integer studentId;
    private Integer status;
    private String repairsTime;
    private Integer isRepairs;
    private String context;
    private String name;
    private String remark;
    private String number;

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

    @Column(name = "repairs_time")
    public String getRepairsTime() {
        return repairsTime;
    }

    public void setRepairsTime(String repairsTime) {
        this.repairsTime = repairsTime;
    }

    @Column(name = "is_repairs")
    public Integer getIsRepairs() {
        return isRepairs;
    }

    public void setIsRepairs(Integer isRepairs) {
        this.isRepairs = isRepairs;
    }


    @Column(name = "context")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
