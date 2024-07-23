package com.cpd.cpd2.usecase;


import java.time.LocalDateTime;

public class FileDsRequestModel {

    private String name;

    private long size;

    private String contentType;

    private byte[] file;

    private LocalDateTime dateOfUpload;
    private String key;


    public FileDsRequestModel(String name,long size,String contentType, byte[] file, LocalDateTime dateOfUpload,
                              String key){
        this.name = name;
        this.size =size;
        this.contentType = contentType;
        this.dateOfUpload = dateOfUpload;
        this.key = key;
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDateOfUpload(LocalDateTime dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public LocalDateTime getDateOfUpload() {
        return dateOfUpload;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

}
