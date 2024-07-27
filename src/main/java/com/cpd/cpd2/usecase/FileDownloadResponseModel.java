package com.cpd.cpd2.usecase;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDownloadResponseModel {
    String name;
    byte[] file;
}
