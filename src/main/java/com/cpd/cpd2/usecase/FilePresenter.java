package com.cpd.cpd2.usecase;

public interface FilePresenter {
    FileResponseModel prepareSuccessView(FileResponseModel user);

    FileResponseModel prepareFailView(String error);
}
