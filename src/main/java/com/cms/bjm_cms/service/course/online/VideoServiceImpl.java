package com.cms.bjm_cms.service.course.online;

import com.cms.bjm_cms.dao.course.online.VideoDao;
import com.cms.bjm_cms.entity.course.online.Video;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


