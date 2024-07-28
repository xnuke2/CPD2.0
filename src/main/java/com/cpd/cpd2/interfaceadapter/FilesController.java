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
import java.time.LocalDateTime;
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
    @PostMapping("/files/upload")
    public ResponseEntity<FileUploadResponseModel> uploadFile(@ModelAttribute MultipartFile file,
                                                      @RequestParam(value="days",required = false) String rowDays,
                                                      @RequestParam(value="hours",required = false) String rowHours,
                                                      @RequestParam(value="minutes",required = false) String rowMinutes
    ) throws IOException {
        try {
            FileUploadRequestModel requestModel;
            if(rowDays==null&&rowHours==null&&rowMinutes==null){
                requestModel = new FileUploadRequestModel(
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType(),
                        file.getBytes());
            }else {
                short days;
                short hours;
                short minutes;
                if(rowDays==null)days=0;else days = Short.parseShort(rowDays);
                if(rowHours==null)hours=0;else hours = Short.parseShort(rowHours);
                if(rowMinutes==null)minutes=0;else minutes = Short.parseShort(rowMinutes);
                requestModel = new FileUploadRequestModel(
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType(),
                        file.getBytes(),
                        LocalDateTime.now().plusDays(days).plusHours(hours).plusMinutes(minutes));
            }
            return new ResponseEntity<>(fileInputBoundary.upload(requestModel),HttpStatus.CREATED);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
