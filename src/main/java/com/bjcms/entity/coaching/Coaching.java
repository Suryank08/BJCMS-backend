package com.bjcms.entity.coaching;

import com.bjcms.entity.course.Course;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.instructor.InstructorInfo;
import com.bjcms.entity.student.Student;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coaching")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "coachingId")
public class Coaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coaching_id")
    private Integer coachingId;
    @Column(name = "coaching_name")
    private String coachingName;
    @Column(name = "coaching_address")
    private String coachingAddress;
    @Column(name = "coaching_vision",length = 700)
    private String coachingVision;

    @OneToOne
    @JoinColumn(name ="coaching_admin_id")
    @JsonIgnore
    private CoachingAdmin coachingAdmin;

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "coaching_instructor",joinColumns = @JoinColumn(name = "coaching_id"),inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    @JsonIgnore
    private List<Instructor> instructorList=new ArrayList<>();

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "coaching_student",joinColumns = @JoinColumn(name = "coaching_id"),inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonIgnore
    private List<Student> studentList=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            },mappedBy = "coaching")
    @JsonIgnore
    private List<Course> coursesList;

    public Coaching(Integer coachingId, String coachingName, String coachingAddress, String coachingVision, CoachingAdmin coachingAdmin, List<Instructor> instructorList, List<Student> studentList, List<Course> coursesList) {
        this.coachingId = coachingId;
        this.coachingName = coachingName;
        this.coachingAddress = coachingAddress;
        this.coachingVision = coachingVision;
        this.coachingAdmin = coachingAdmin;
        this.instructorList = instructorList;
        this.studentList = studentList;
        this.coursesList = coursesList;
    }

    public Coaching() {
    }

    public Integer getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(Integer coachingId) {
        this.coachingId = coachingId;
    }

    public String getCoachingName() {
        return coachingName;
    }

    public void setCoachingName(String coachingName) {
        this.coachingName = coachingName;
    }

    public String getCoachingAddress() {
        return coachingAddress;
    }

    public void setCoachingAddress(String coachingAddress) {
        this.coachingAddress = coachingAddress;
    }

    public String getCoachingVision() {
        return coachingVision;
    }

    public void setCoachingVision(String coachingVision) {
        this.coachingVision = coachingVision;
    }

    public CoachingAdmin getCoachingAdmin() {
        return coachingAdmin;
    }

    public void setCoachingAdmin(CoachingAdmin coachingAdmin) {
        this.coachingAdmin = coachingAdmin;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
