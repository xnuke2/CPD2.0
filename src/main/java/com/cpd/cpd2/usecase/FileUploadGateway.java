package com.cpd.cpd2.usecase;

public interface FileUploadGateway {

    boolean ExistById(String Key);

    void Upload(FileDsRequestModel fileDsRequestModel);

}
