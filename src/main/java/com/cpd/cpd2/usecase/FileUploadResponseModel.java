package com.cpd.cpd2.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FileUploadResponseModel {
    LocalDateTime CreationTime;

    private String name;

    public FileUploadResponseModel(String name, LocalDateTime creationTime) {
         this.name = name;
         this.CreationTime = creationTime;
    }


}

