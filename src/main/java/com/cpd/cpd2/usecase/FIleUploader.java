package com.cpd.cpd2.usecase;


import com.cpd.cpd2.entity.CommonFileInfoFactory;
import com.cpd.cpd2.entity.FileInfo;
import com.cpd.cpd2.entity.FileInfoFactory;

import java.time.LocalDateTime;


public class FIleUploader implements FileInputBoundary{
    final FileInfoFactory fileFactory;
    final FilePresenter filePresenter;
    final FileUploadGateway fileUploadGateway;
    final FileHashing fileHashing;


    public FIleUploader(FilePresenter filePresenter, FileUploadGateway fileUploadGateway,FileHashing fileHashing){
         this.fileFactory = new CommonFileInfoFactory();
         this.filePresenter = filePresenter;
         this.fileUploadGateway = fileUploadGateway;
         this.fileHashing = fileHashing;
    }

    public FileResponseModel Upload(FileRequestModel request){
        FileInfo fileInfo = fileFactory.Create(request.getName(), request.getSize(), request.getContentType());
        if(!fileInfo.CheckData()){
            return filePresenter.prepareFailView("Incorrect name or size or type");
        }
        String key = fileHashing.GenerateKey(request.getName());
        if(fileUploadGateway.ExistById(key)){
            return filePresenter.prepareFailView("An error has occurred. Please try again later");
        }
        LocalDateTime now = LocalDateTime.now();

        FileDsRequestModel fileDsRequestModel = new FileDsRequestModel(request.getName(), request.getSize(),
                request.getContentType(), request.getFile(),now,key);

        fileUploadGateway.Upload(fileDsRequestModel);
        FileResponseModel tmp =  new FileResponseModel();
        tmp.setCreationTime(request.getName(),now);
        return filePresenter.prepareSuccessView(tmp);
    }
}
