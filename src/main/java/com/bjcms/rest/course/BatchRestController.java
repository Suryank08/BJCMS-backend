package com.bjcms.rest.course;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjcms.entity.course.offline.Batch;
import com.bjcms.service.course.offline.BatchService;

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
