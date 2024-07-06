package com.cms.bjm_cms.dao.course.online;

import com.cms.bjm_cms.entity.course.online.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDao extends JpaRepository<Video,Integer> {
}
