package com.cpd.cpd2.usecase;


import com.cpd.cpd2.entity.CommonFileInfoFactory;
import com.cpd.cpd2.entity.FileInfo;
import com.cpd.cpd2.entity.FileInfoFactory;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class FileUploaderDownloader implements FileInputBoundary{
    final FileInfoFactory fileFactory= new CommonFileInfoFactory();
    final FilePresenter filePresenter;
    final FileUploadDownloadService fileUploadDownloadService;
    final FileHashing fileHashing;

    public FileUploadResponseModel upload(FileUploadRequestModel request){
        FileInfo fileInfo = fileFactory.create(request.getName(), request.getSize(), request.getContentType());
        if(!fileInfo.checkData()){
            return filePresenter.prepareFailView("Incorrect name or size or type");
        }
        String key = fileHashing.generateKey(request.getName());
        if(fileUploadDownloadService.existById(key)){
            return filePresenter.prepareFailView("An error has occurred. Please try again later");
        }
        LocalDateTime now = LocalDateTime.now();

        FileDsRequestModel fileDsRequestModel = new FileDsRequestModel(request.getName(), request.getSize(),
                request.getContentType(), request.getFile(),now,key,request.getDateOfRemove());

        fileUploadDownloadService.upload(fileDsRequestModel);
        FileUploadResponseModel tmp =  new FileUploadResponseModel(request.getName(),now);
        return filePresenter.prepareSuccessView(tmp);
    }
    public FileDownloadResponseModel download(String id){
        FileDownloadResponseModel tmpFile = fileUploadDownloadService.download(id);
        if(tmpFile==null||tmpFile.getFile()==null||tmpFile.getName()==null)
            return filePresenter.prepareDownloadFailView("No file with given id");
        return filePresenter.prepareSuccessView(tmpFile);
    }
    public List<FileInfoEntity> getAllFiles(){
        return fileUploadDownloadService.getAllFiles();
    }

    public boolean delete(String id){
        return fileUploadDownloadService.delete(id);
    }

}
