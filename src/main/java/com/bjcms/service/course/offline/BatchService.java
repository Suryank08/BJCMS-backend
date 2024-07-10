package com.bjcms.service.course.offline;

import java.util.List;

import com.bjcms.entity.course.offline.Batch;

public interface BatchService {
    public List<Batch> addBatch(List<Batch> batchList);

    public void deleteBatch(int id);

    public Batch updateBatch(Batch batch);

    public Batch findBatch(int id);

    public List<Batch> getAllBatch();
    ////hsdfhdhsfhjid
}
