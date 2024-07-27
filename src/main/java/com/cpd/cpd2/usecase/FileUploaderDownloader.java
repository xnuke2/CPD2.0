package com.cpd.cpd2.usecase;


import com.cpd.cpd2.entity.CommonFileInfoFactory;
import com.cpd.cpd2.entity.FileInfo;
import com.cpd.cpd2.entity.FileInfoFactory;

import java.time.LocalDateTime;
import java.util.List;


public class FileUploaderDownloader implements FileInputBoundary{
    final FileInfoFactory fileFactory;
    final FilePresenter filePresenter;
    final FileUploadDownloadService fileUploadDownloadService;
    final FileHashing fileHashing;


    public FileUploaderDownloader(FilePresenter filePresenter, FileUploadDownloadService fileUploadDownloadService, FileHashing fileHashing){
         this.fileFactory = new CommonFileInfoFactory();
         this.filePresenter = filePresenter;
         this.fileUploadDownloadService = fileUploadDownloadService;
         this.fileHashing = fileHashing;
    }

    public FileUploadResponseModel upload(FileUploadRequestModel request){
        FileInfo fileInfo = fileFactory.Create(request.getName(), request.getSize(), request.getContentType());
        if(!fileInfo.CheckData()){
            return filePresenter.prepareFailView("Incorrect name or size or type");
        }
        String key = fileHashing.generateKey(request.getName());
        if(fileUploadDownloadService.existById(key)){
            return filePresenter.prepareFailView("An error has occurred. Please try again later");
        }
        LocalDateTime now = LocalDateTime.now();

        FileDsRequestModel fileDsRequestModel = new FileDsRequestModel(request.getName(), request.getSize(),
                request.getContentType(), request.getFile(),now,key);

        fileUploadDownloadService.upload(fileDsRequestModel);
        FileUploadResponseModel tmp =  new FileUploadResponseModel(request.getName(),now);
        return filePresenter.prepareSuccessView(tmp);
    }
//    public FileDownloadResponseModel download(String id){
//        FileDownloadResponseModel tmpFile = fileUploadDownloadService.download(id);
//        if(tmpFile==null||tmpFile.getFile()==null||tmpFile.getName()==null)
//            return filePresenter.prepareDownloadFailView("No file with given id");
//        return filePresenter.prepareSuccessView(tmpFile);
//    }
    public List<FileInfoEntity> getAllFiles(){
        List<FileInfoEntity> tmp = fileUploadDownloadService.getAllFiles();
        return tmp;
    }

}
