package com.bjcms.service.course.online;

import com.bjcms.dao.course.online.CommentDao;
import com.bjcms.entity.course.online.Comment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional
    public List<Comment> addComment(List<Comment> commentList) {
        for (Comment comment : commentList) {
            commentDao.save(comment);
        }
        return commentDao.findAll();
    }

    @Transactional
    public Comment updateComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Transactional
    public void deleteComment(int id) {
        Optional<Comment> optionalComment = commentDao.findById(id);
        Comment comment = optionalComment.orElse(new Comment());
        commentDao.delete(comment);
    }

    public List<Comment> getAllComments() {
        List<Comment> commentList = commentDao.findAll();
        return commentList;
    }

    public Comment findComment(int id) {
        Optional<Comment> optionalComment = commentDao.findById(id);
        Comment comment = optionalComment.orElse(new Comment());
        return comment;
    }
}
