package com.cpd.cpd2.entity;

public class CommonFileInfo implements FileInfo {

    private String name;

    private long size;

    private String type;

    public Boolean CheckData(){
        return !(name == null || size == 0 || type ==null);
    }

    CommonFileInfo(String name, long size, String type){
        this.name = name;
        this.size=size;
        this.type = type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }
}
