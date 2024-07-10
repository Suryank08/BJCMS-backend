package com.bjcms.entity.course.online;

import java.util.ArrayList;
import java.util.List;

import com.bjcms.entity.course.Subject;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "video")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "videoId")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Integer videoId;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "likes")
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "course_id")

    private OnlineCourse onlineCourse;

    @ManyToOne
    @JoinColumn(name = "subject_id")

    private Subject subject;

    @OneToMany(mappedBy ="video")

    private List<Comment> commentList=new ArrayList<>();

    public Video(Integer videoId, String videoUrl, Integer likes, OnlineCourse onlineCourse, Subject subject, List<Comment> commentList) {
        this.videoId = videoId;
        this.videoUrl = videoUrl;
        this.likes = likes;
        this.onlineCourse = onlineCourse;
        this.subject = subject;
        this.commentList = commentList;
    }

    public Video() {
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public OnlineCourse getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
