package com.cpd.cpd2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonFileInfo implements FileInfo {
    private String name;
    private long size;
    private String type;

    public Boolean checkData(){
        return !(name == null || size == 0 || type ==null);
    }
}
