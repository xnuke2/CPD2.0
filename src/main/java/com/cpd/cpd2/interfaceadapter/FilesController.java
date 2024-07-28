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
public class FilesController {

    final FileInputBoundary fileInputBoundary;

    @Autowired
    public FilesController(JpaFile jpaFile){
        FilePresenter filePresenter = new FileResponseFormatter();
        CommonHashingFile commonHashingFile = new CommonHashingFile();
        this.fileInputBoundary =  new FileUploaderDownloader(filePresenter,jpaFile, commonHashingFile);

    }
    @PostMapping("/fileUploader")
    ResponseEntity<FileUploadResponseModel> create(@ModelAttribute MultipartFile file) throws IOException {
        FileUploadRequestModel requestModel = new FileUploadRequestModel(file.getOriginalFilename(),file.getSize(),file.getContentType(),
                file.getBytes());
        return new ResponseEntity<>(fileInputBoundary.upload(requestModel),HttpStatus.CREATED);
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

        return files != null &&  !files.isEmpty()
                ? new ResponseEntity<>(files, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> getListFiles(@PathVariable String id){
        boolean deleted =fileInputBoundary.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id){
        FileDownloadResponseModel file =fileInputBoundary.download(id);
        return file!=null
                ? new ResponseEntity<>(file.getFile(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }





}
