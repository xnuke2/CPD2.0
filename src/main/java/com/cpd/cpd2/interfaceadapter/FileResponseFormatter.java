package com.cpd.cpd2.interfaceadapter;

import com.cpd.cpd2.usecase.FilePresenter;
import com.cpd.cpd2.usecase.FileResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileResponseFormatter implements FilePresenter {
    @Override
    public FileResponseModel prepareSuccessView(FileResponseModel response) {
//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    @Override
    public FileResponseModel prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
