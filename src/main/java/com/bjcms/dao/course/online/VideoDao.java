package com.bjcms.dao.course.online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.online.Video;

@Repository
public interface VideoDao extends JpaRepository<Video,Integer> {
}
