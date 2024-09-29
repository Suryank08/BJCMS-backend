package com.bjcms.entity.coaching;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "coaching_admin")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "coachingAdminId")
public class CoachingAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coaching_admin_id")
    private Integer coachingAdminId;
    @Column(name = "coaching_admin_name" ,nullable = false)
    private String coachingAdminName;
    @Email
    @Column(name = "coaching_admin_email",nullable = false)
    private String coachingAdminEmail;
    @Column(name = "mob_num",nullable = false)
    private String mobileNumber;
    @OneToOne(mappedBy = "coachingAdmin")
    private Coaching coaching;

    public CoachingAdmin(Integer coachingAdminId, String coachingAdminName, String coachingAdminEmail, String mobileNumber, Coaching coaching) {
        this.coachingAdminId = coachingAdminId;
        this.coachingAdminName = coachingAdminName;
        this.coachingAdminEmail = coachingAdminEmail;
        this.mobileNumber = mobileNumber;
        this.coaching = coaching;
    }

    public CoachingAdmin() {
    }

    public Integer getCoachingAdminId() {
        return coachingAdminId;
    }

    public void setCoachingAdminId(Integer coachingAdminId) {
        this.coachingAdminId = coachingAdminId;
    }

    public String getCoachingAdminName() {
        return coachingAdminName;
    }

    public void setCoachingAdminName(String coachingAdminName) {
        this.coachingAdminName = coachingAdminName;
    }

    public @Email String getCoachingAdminEmail() {
        return coachingAdminEmail;
    }

    public void setCoachingAdminEmail(@Email String coachingAdminEmail) {
        this.coachingAdminEmail = coachingAdminEmail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Coaching getCoaching() {
        return coaching;
    }

    public void setCoaching(Coaching coaching) {
        this.coaching = coaching;
    }
}
