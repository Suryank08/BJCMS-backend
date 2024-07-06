package com.cms.bjm_cms.service.course.online;

import com.cms.bjm_cms.entity.course.online.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> addComment(List<Comment> commentList);
    Comment updateComment(Comment comment);
    void deleteComment(int id);
    List<Comment> getAllComments();
    Comment findComment(int id);
}
