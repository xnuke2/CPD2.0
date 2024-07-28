package com.cpd.cpd2.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FileUploadRequestModel {

    private String name;

    private long size;

    private String contentType;

    private byte[] file;

    private LocalDateTime dateOfRemove;
    public FileUploadRequestModel(String name,long size,String contentType,byte[] file){
        this.name =name;
        this.size =size;
        this.contentType=contentType;
        this.file=file;
        dateOfRemove =null;
    }
}
