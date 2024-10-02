package com.bjcms.dao.course.online;

import com.bjcms.entity.course.online.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment,Integer> {
}
