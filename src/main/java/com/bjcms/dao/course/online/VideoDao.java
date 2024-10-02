package com.bjcms.dao.course.online;

import com.bjcms.entity.course.online.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDao extends JpaRepository<Video,Integer> {
}
