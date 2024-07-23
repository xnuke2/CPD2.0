package com.cpd.cpd2.interfaceadapter;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "Files")
public class FileInfoMapper {
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "date_of_upload")
    private LocalDateTime dateOfUpload;

    public FileInfoMapper(){}

    public FileInfoMapper(String key,String name,long size,String contentType,LocalDateTime dateOfUpload){
        this.key = key;
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.dateOfUpload = dateOfUpload;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(LocalDateTime dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
