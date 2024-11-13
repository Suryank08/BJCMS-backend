package com.bjcms.dto.course;

public class BatchDto {

    private Integer batchId;

    private String batchName;

    private String batchTime;

    public BatchDto(Integer batchId, String batchName, String batchTime) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.batchTime = batchTime;
    }

    public BatchDto() {
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }
}
