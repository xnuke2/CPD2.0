package com.cpd.cpd2.entity;

public interface FileInfoFactory {
    FileInfo Create(String name, long size, String type);
}
