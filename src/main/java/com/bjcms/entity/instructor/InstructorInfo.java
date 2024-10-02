package com.bjcms.entity.instructor;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_info")
public class InstructorInfo {

    @Id
    @Column(name = "instructor_id")
    private Integer instructorId;

    @Column(name = "instructor_info")
    private String instructorInfoDetails;

    @OneToOne
    @MapsId // Used when primary key is also used as foreign key & Use the same identifier as the Instructor entity & Maps instructorId as the primary key of this entity
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public InstructorInfo() {
    }

    public InstructorInfo(Integer instructorId, String instructorInfoDetails, Instructor instructor) {
        this.instructorId = instructorId;
        this.instructorInfoDetails = instructorInfoDetails;
        this.instructor = instructor;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorInfoDetails() {
        return instructorInfoDetails;
    }

    public void setInstructorInfoDetails(String instructorInfoDetails) {
        this.instructorInfoDetails = instructorInfoDetails;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
