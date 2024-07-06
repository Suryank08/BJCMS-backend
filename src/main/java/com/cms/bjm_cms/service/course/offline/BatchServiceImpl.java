package com.cms.bjm_cms.service.course.offline;


import com.cms.bjm_cms.dao.course.offline.BatchDao;
import com.cms.bjm_cms.entity.course.offline.Batch;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchServiceImpl implements BatchService {
    BatchDao batchDao;

    @Autowired
    public BatchServiceImpl(BatchDao batchDao) {
        this.batchDao = batchDao;
    }

    @Transactional
    public List<Batch> addBatch(List<Batch> batchList) {
        for (Batch batch : batchList) {
            batchDao.save(batch);
        }
        return batchDao.findAll();
    }

    @Transactional
    public Batch updateBatch(Batch batch) {
        return batchDao.save(batch);
    }

    @Transactional
    public void deleteBatch(int id) {
        Optional<Batch> optionalBatch = batchDao.findById(id);
        Batch batch = optionalBatch.orElse(new Batch());
        batchDao.delete(batch);
    }

    public List<Batch> getAllBatch() {
        List<Batch> batchList = batchDao.findAll();
        return batchList;
    }


    public Batch findBatch(int id) {
        Optional<Batch> optionalBatch = batchDao.findById(id);
        Batch batch = optionalBatch.orElse(new Batch());
        return batch;
    }

}
