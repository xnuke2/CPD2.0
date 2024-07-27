package com.cpd.cpd2.interfaceadapter;


import com.cpd.cpd2.usecase.FileDownloadResponseModel;
import com.cpd.cpd2.usecase.FilePresenter;
import com.cpd.cpd2.usecase.FileUploadResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileResponseFormatter implements FilePresenter {

    @Override
    public FileUploadResponseModel prepareSuccessView(FileUploadResponseModel response) {
//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    @Override
    public FileUploadResponseModel prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }

    @Override
    public FileDownloadResponseModel prepareSuccessView(FileDownloadResponseModel response){
        return response;
    }

    @Override
    public FileDownloadResponseModel prepareDownloadFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
