package com.bjcms.dao.course.online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.online.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment,Integer> {
}
