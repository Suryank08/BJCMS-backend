package com.bjcms.service.course.online;

import java.util.List;

import com.bjcms.entity.course.online.Video;

public interface VideoService {
    List<Video> addVideos(List<Video> videoList);
    Video updateVideo(Video video);
    void deleteVideo(int id);
    List<Video> getAllVideos();
    Video findVideo(int id);
}

