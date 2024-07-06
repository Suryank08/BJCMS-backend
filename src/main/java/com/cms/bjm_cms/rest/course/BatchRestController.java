package com.cms.bjm_cms.rest.course;


import com.cms.bjm_cms.entity.course.offline.Batch;
import com.cms.bjm_cms.service.course.offline.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BatchRestController {
    private BatchService batchService;

    @Autowired
    public BatchRestController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/batchs")
    public List<Batch> getAllBatch(){
        return batchService.getAllBatch();
    }
    @GetMapping("/batchs/{batchId}")
    public Batch getBatch(@PathVariable int batchId){
        return batchService.findBatch(batchId);
    }
//    @PostMapping("/batchs")
//    public List<Batch> addBatch(@RequestBody List<Batch> batchList){
//        System.out.println("Added Batch");
//        return batchService.addBatch(batchList);
//
//    }
    @DeleteMapping("/batchs/{batchId}")
    public void deleteBatch(@PathVariable int batchId){
        batchService.deleteBatch(batchId);
        System.out.println("Batch Deleted");
    }
    @PutMapping("/batchs")
    public Batch updateBatch(@RequestBody Batch batch){
        return batchService.updateBatch(batch);
    }
}
