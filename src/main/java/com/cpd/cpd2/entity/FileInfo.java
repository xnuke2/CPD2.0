package com.cpd.cpd2.entity;

public interface FileInfo {

    Boolean checkData();

    long getSize();

    String getName();

    String getType();

    void setName(String name);

    void setSize(long size);

    void setType(String type);

}
