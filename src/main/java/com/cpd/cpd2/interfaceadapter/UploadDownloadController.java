package com.cpd.cpd2.interfaceadapter;


import com.cpd.cpd2.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
public class UploadDownloadController {

    final FileInputBoundary fileInputBoundary;

    @Autowired
    public UploadDownloadController(JpaFile jpaFile){
        FilePresenter filePresenter = new FileResponseFormatter();

        JpaFile fileUploadGateway= jpaFile;
        CommonHashingFile commonHashingFile = new CommonHashingFile();
        this.fileInputBoundary =  new FileUploaderDownloader(filePresenter,fileUploadGateway, commonHashingFile);

    }
    @PostMapping("/fileUploader")
    FileUploadResponseModel create(@ModelAttribute MultipartFile file) throws IOException {
        FileUploadRequestModel requestModel = new FileUploadRequestModel(file.getOriginalFilename(),file.getSize(),file.getContentType(),
                file.getBytes());
        return fileInputBoundary.upload(requestModel);
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileInfoEntity>> getListFiles(){
        List<FileInfoEntity> files = fileInputBoundary.getAllFiles();
        for (FileInfoEntity tmp:files) {
            tmp.setKey(ServletUriComponentsBuilder
                              .fromCurrentContextPath()
                              .path("/files/")
                              .path(tmp.getKey())
                              .toUriString());
        }

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

//    @GetMapping("/files/{id}")
//    public ResponseEntity<byte[]> download(@PathVariable String id){
//        FileDownloadResponseModel file =fileInputBoundary.download(id);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
//                .body(file.getFile());
//    }



}
