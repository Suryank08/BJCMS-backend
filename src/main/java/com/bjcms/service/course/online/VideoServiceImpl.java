package com.bjcms.service.course.online;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcms.dao.course.online.VideoDao;
import com.bjcms.entity.course.online.Video;

import jakarta.transaction.Transactional;

@Service
public class VideoServiceImpl implements VideoService {
    VideoDao videoDao;

    @Autowired
    public VideoServiceImpl(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @Transactional
    public List<Video> addVideos(List<Video> videoList) {
        for (Video video : videoList) {
            videoDao.save(video);
        }
        return videoDao.findAll();
    }

    @Transactional
    public Video updateVideo(Video video) {
        return videoDao.save(video);
    }

    @Transactional
    public void deleteVideo(int id) {
        Optional<Video> optionalVideo = videoDao.findById(id);
        Video video = optionalVideo.orElse(new Video());
        videoDao.delete(video);
    }

    public List<Video> getAllVideos() {
        List<Video> videoList = videoDao.findAll();
        return videoList;
    }

    public Video findVideo(int id) {
        Optional<Video> optionalVideo = videoDao.findById(id);
        Video video = optionalVideo.orElse(new Video());
        return video;
    }
}


