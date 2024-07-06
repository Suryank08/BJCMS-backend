package com.cms.bjm_cms.service.course.offline;

import com.cms.bjm_cms.entity.course.offline.Batch;

import java.util.List;

public interface BatchService {
    public List<Batch> addBatch(List<Batch> batchList);

    public void deleteBatch(int id);

    public Batch updateBatch(Batch batch);

    public Batch findBatch(int id);

    public List<Batch> getAllBatch();
}
