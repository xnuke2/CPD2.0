package com.cpd.cpd2.usecase;

import java.util.List;

public interface FileUploadDownloadService {

    boolean existById(String Key);

    void upload(FileDsRequestModel fileDsRequestModel);

    FileDownloadResponseModel download(String id);

    List<FileInfoEntity> getAllFiles();

    boolean delete(String id);

}
