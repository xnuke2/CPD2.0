package com.cpd.cpd2.usecase;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FileDsRequestModel {

    private String name;

    private long size;

    private String contentType;

    private byte[] file;

    private LocalDateTime dateOfUpload;

    private String key;

    private LocalDateTime dateOfRemove;
}
