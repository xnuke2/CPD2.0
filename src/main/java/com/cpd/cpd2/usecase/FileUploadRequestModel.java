package com.cpd.cpd2.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileUploadRequestModel {

    private String name;

    private long size;

    private String contentType;

    private byte[] file;
}
