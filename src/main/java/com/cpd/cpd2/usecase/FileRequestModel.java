package com.cpd.cpd2.usecase;

public class FileRequestModel {

    private String name;

    private long size;

    private String contentType;

    private byte[] file;

    public FileRequestModel(String name,long size, String contentType, byte[] file){
        this.name = name;
        this.size= size;
        this.contentType = contentType;
        this.file= file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }


}
