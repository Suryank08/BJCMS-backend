package com.bjcms.entity.course.online;

import java.time.LocalDateTime;

import com.bjcms.entity.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "comment_text")
    private String commentText;
    @Column(name="time_stamp")
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

 public Comment(Integer commentId, String commentText, LocalDateTime timeStamp, Video video, Student student) {
  this.commentId = commentId;
  this.commentText = commentText;
  this.timeStamp = timeStamp;
  this.video = video;
  this.student = student;
 }

 public Comment() {
 }

 public Integer getCommentId() {
  return commentId;
 }

 public void setCommentId(Integer commentId) {
  this.commentId = commentId;
 }

 public String getCommentText() {
  return commentText;
 }

 public void setCommentText(String commentText) {
  this.commentText = commentText;
 }

 public LocalDateTime getTimeStamp() {
  return timeStamp;
 }

 public void setTimeStamp(LocalDateTime timeStamp) {
  this.timeStamp = timeStamp;
 }

 public Video getVideo() {
  return video;
 }

 public void setVideo(Video video) {
  this.video = video;
 }

 public Student getStudent() {
  return student;
 }

 public void setStudent(Student student) {
  this.student = student;
 }
}
