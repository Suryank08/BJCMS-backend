package com.bjcms.service.course.online;

import java.util.List;

import com.bjcms.entity.course.online.Comment;

public interface CommentService {
    List<Comment> addComment(List<Comment> commentList);
    Comment updateComment(Comment comment);
    void deleteComment(int id);
    List<Comment> getAllComments();
    Comment findComment(int id);
}
