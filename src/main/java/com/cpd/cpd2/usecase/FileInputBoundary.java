package com.cpd.cpd2.usecase;

import java.util.List;

public interface FileInputBoundary {
    FileUploadResponseModel upload(FileUploadRequestModel request);

    FileDownloadResponseModel download(String id);

    FileDownloadResponseModel downloadOneTime(String id);

    List<FileInfoEntity> getAllFiles();

    boolean delete(String id);

    String generateOneTimeIdForDownload(String id);

}
