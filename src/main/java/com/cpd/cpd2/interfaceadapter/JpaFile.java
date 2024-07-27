package com.cpd.cpd2.interfaceadapter;

import com.cpd.cpd2.interfaceadapter.repository.JpaFileRepository;
import com.cpd.cpd2.interfaceadapter.repository.MinioComponent;
import com.cpd.cpd2.usecase.FileDsRequestModel;
import com.cpd.cpd2.usecase.FileInfoEntity;
import com.cpd.cpd2.usecase.FileUploadDownloadService;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Component
@Getter
@Setter
public class JpaFile implements FileUploadDownloadService {
    @Autowired
    private MinioComponent minioComponent;

    @Autowired
    private JpaFileRepository repository;

    @Override
    public  boolean existById(String Key){
        return repository.existsById(Key);
    }

    @Override
    public void upload(@NotNull FileDsRequestModel fileDsRequestModel) {
        FileInfoEntity fileInfo  = new FileInfoEntity(fileDsRequestModel.getKey(), fileDsRequestModel.getName(),
                fileDsRequestModel.getSize(), fileDsRequestModel.getContentType(),fileDsRequestModel.getDateOfUpload());
        try {
            if(!(uploadFileToMinIO(fileDsRequestModel.getKey(), fileDsRequestModel.getFile()))){
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        this.repository.save(fileInfo);
    }
//    @Override
//    public FileDownloadResponseModel download(String id){
//        Optional<FileInfoEntity> optional = this.repository.findById(id);
//        FileInfoEntity fileInfo=optional.get();
//        byte[] file = minioComponent.getObject(id);
//        return new FileDownloadResponseModel(fileInfo.getName(),file);
//    }
    @Override
    public List<FileInfoEntity> getAllFiles(){
        return repository.findAll();
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



}
