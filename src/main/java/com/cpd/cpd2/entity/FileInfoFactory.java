package com.cpd.cpd2.entity;

public interface FileInfoFactory {
    FileInfo create(String name, long size, String type);
}
