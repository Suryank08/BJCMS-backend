package com.bjcms.service.course.online;

import com.bjcms.entity.course.online.Video;

import java.util.List;

public interface VideoService {
    List<Video> addVideos(List<Video> videoList);
    Video updateVideo(Video video);
    void deleteVideo(int id);
    List<Video> getAllVideos();
    Video findVideo(int id);
}

