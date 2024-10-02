package com.bjcms.service.course.offline;

import com.bjcms.entity.course.offline.Batch;

import java.util.List;

public interface BatchService {
    public List<Batch> addBatch(List<Batch> batchList);
    public Batch addBatch(Batch batch);

    public void deleteBatch(int id);

    public Batch updateBatch(Batch batch);

    public Batch findBatch(int id);

    public List<Batch> getAllBatch();

}
