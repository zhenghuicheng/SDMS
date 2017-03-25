package sdms.entity.admin;

import javax.persistence.*;

/**
 * Created by cheng on 2017/2/3.
 */
@Entity
@Table(name = "visit_record")
public class VisitRecord {

    private Integer id;
    private Integer dormitoryId;
    private String visitor;
    private String visitorNumber;
    private String visitedName;
    private String startTime;
    private String endTime;
    private Integer status;

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

    @Column(name = "visitor")
    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    @Column(name = "visitor_number")
    public String getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(String visitorNumber) {
        this.visitorNumber = visitorNumber;
    }

    @Column(name = "visited_name")
    public String getVisitedName() {
        return visitedName;
    }

    public void setVisitedName(String visitedName) {
        this.visitedName = visitedName;
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
}
