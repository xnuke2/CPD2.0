package com.cpd.cpd2.usecase;

public interface FilePresenter {
    FileUploadResponseModel prepareSuccessView(FileUploadResponseModel user);

    FileUploadResponseModel prepareFailView(String error);

    FileDownloadResponseModel prepareSuccessView(FileDownloadResponseModel answer);

    FileDownloadResponseModel prepareDownloadFailView(String error);
}
