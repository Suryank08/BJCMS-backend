package com.bjcms.rest.course;


import com.bjcms.entity.course.offline.Batch;
import com.bjcms.service.course.offline.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/batchs")
public class BatchRestController {
    private BatchService batchService;

    @Autowired
    public BatchRestController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Batch addBatch(@RequestBody Batch batch) {
        return batchService.addBatch(batch);
    }

    @PostMapping("/bulk-create")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<Batch> addBatchList(@RequestBody List<Batch> batchList) {
        return batchService.addBatch(batchList);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Batch updateBatch(@RequestBody Batch batch) {
        return batchService.updateBatch(batch);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public List<Batch> getAllBatch() {
        return batchService.getAllBatch();
    }

    @GetMapping("/{batchId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public Batch getBatch(@PathVariable Integer batchId) {
        return batchService.findBatch(batchId);
    }

    @DeleteMapping("/delete/{batchId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBatch(@PathVariable Integer batchId) {
        batchService.deleteBatch(batchId);
    }

}
