package com.cpd.cpd2.entity;

import java.time.LocalDateTime;

public class CommonFileInfoFactory implements FileInfoFactory {
    @Override
    public FileInfo create(String name, long size, String type){
        return new CommonFileInfo(name, size, type);
    }


}
