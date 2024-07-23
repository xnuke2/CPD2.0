package com.cpd.cpd2.interfaceadapter;

import com.cpd.cpd2.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class UploadController {

    final FileInputBoundary fileInputBoundary;

    @Autowired
    public UploadController(JpaFile jpaFile){
        FilePresenter filePresenter = new FileResponseFormatter();

        JpaFile fileUploadGateway= jpaFile;
        CommonHashingFile commonHashingFile = new CommonHashingFile();
        this.fileInputBoundary =  new FIleUploader(filePresenter,fileUploadGateway, commonHashingFile);

    }
    @PostMapping("/fileUploader")
    FileResponseModel create(@ModelAttribute MultipartFile file) throws IOException {
        FileRequestModel requestModel = new FileRequestModel(file.getOriginalFilename(),file.getSize(),file.getContentType(),
                file.getBytes());
        return fileInputBoundary.Upload(requestModel);
    }

//    @GetMapping(value ="/fileUploader",produces = APPLICATION_JSON_VALUE)
//    @ResponseBody FileResponseModel create1() {
//        FileResponseModel tmp =  new FileResponseModel();
//        tmp.setCreationTime("ok", LocalDateTime.now());
//        return tmp;
//    }

}
