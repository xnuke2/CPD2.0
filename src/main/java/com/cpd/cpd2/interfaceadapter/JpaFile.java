package com.cpd.cpd2.interfaceadapter;

import com.cpd.cpd2.usecase.FileDsRequestModel;
import com.cpd.cpd2.usecase.FileUploadGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class JpaFile implements FileUploadGateway {
    @Autowired
    private MinioComponent minioComponent;

    @Autowired
    private JpaFileRepository repository;

    public JpaFileRepository getRepository() {
        return repository;
    }

    public MinioComponent getMinioComponent() {
        return minioComponent;
    }

    public void setMinioComponent(MinioComponent minioComponent) {
        this.minioComponent = minioComponent;
    }

    public void setRepository(JpaFileRepository repository) {
        this.repository = repository;
    }

    public JpaFile(){};
    public JpaFile(MinioComponent minioComponent,JpaFileRepository repository){
        this.minioComponent = minioComponent;
        this.repository = repository;
    };
    @Override
    public  boolean ExistById(String Key){
        return repository.existsById(Key);
    }

    @Override
    public void Upload(FileDsRequestModel fileDsRequestModel) {
        FileInfoMapper fileInfo  = new FileInfoMapper(fileDsRequestModel.getKey(), fileDsRequestModel.getName(),
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

    public Boolean uploadFileToMinIO(String key, byte[] file) {
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
