package com.cpd.cpd2.interfaceadapter;

import com.cpd.cpd2.interfaceadapter.repository.JpaFileRepository;
import com.cpd.cpd2.interfaceadapter.repository.JpaLinkRepository;
import com.cpd.cpd2.interfaceadapter.repository.MinioComponent;
import com.cpd.cpd2.usecase.FileDownloadResponseModel;
import com.cpd.cpd2.usecase.FileDsRequestModel;
import com.cpd.cpd2.usecase.FileInfoEntity;
import com.cpd.cpd2.usecase.FileUploadDownloadService;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Getter
@Setter
@EnableScheduling
public class JpaFile implements FileUploadDownloadService {
    @Autowired
    private MinioComponent minioComponent;

    @Autowired
    private JpaFileRepository fileRepository;

    @Autowired
    private JpaLinkRepository linkRepository;

    @Override
    public  boolean existById(String Key){
        return fileRepository.existsById(Key);
    }

    @Override
    public void upload(@NotNull FileDsRequestModel fileDsRequestModel) {

        FileInfoEntity fileInfo  = new FileInfoEntity(fileDsRequestModel.getKey(), fileDsRequestModel.getName(),
                fileDsRequestModel.getSize(), fileDsRequestModel.getContentType(),fileDsRequestModel.getDateOfUpload(),
                fileDsRequestModel.getDateOfRemove());
        try {
            if(!(uploadFileToMinIO(fileDsRequestModel.getKey(), fileDsRequestModel.getFile()))){
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        this.fileRepository.save(fileInfo);
    }
    @Override
    public FileDownloadResponseModel download(String id){
        Optional<FileInfoEntity> optional = this.fileRepository.findById(id);
        FileInfoEntity fileInfo=optional.get();
        byte[] file = minioComponent.getObject(id);
        return new FileDownloadResponseModel(fileInfo.getName(),file);
    }
    @Override
    public List<FileInfoEntity> getAllFiles(){
        return fileRepository.findAll();
    }

    @Override
    public boolean delete(String id){
        try {
            fileRepository.deleteById(id);
            minioComponent.deleteObject(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void createOneTimeLink(String linkId, String fileId){
        linkRepository.save(new LinkEntity(linkId,fileId));
    }

    @Override
    public String findFileID(String linkId){
        Optional<LinkEntity> optional = linkRepository.findById(linkId);
        try {
            LinkEntity linkEntity = optional.get();
            linkRepository.deleteById(linkId);
            return linkEntity.getFileID();
        }catch (NoSuchElementException e){
            return null;
        }

    }

    private Boolean uploadFileToMinIO(String key, byte[] file) {
        try {
            InputStream in = new ByteArrayInputStream(file);
            String fileName = key;
            minioComponent.putObject(fileName, in);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Scheduled(fixedRate = 300000)
    void updateFiles(){
        List<FileInfoEntity> allFiles =fileRepository.findAll();
        for (FileInfoEntity fileInfo: allFiles) {
            if(fileInfo.getDateToRemove()!=null)
                if(fileInfo.getDateToRemove().isBefore(LocalDateTime.now())){
                    delete(fileInfo.getKey());
                }
        }
    }




}
