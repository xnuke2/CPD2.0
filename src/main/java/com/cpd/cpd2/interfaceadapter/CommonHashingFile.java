package com.cpd.cpd2.interfaceadapter;

import com.cpd.cpd2.usecase.FileHashing;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

public class CommonHashingFile implements FileHashing {
    public String generateKey(String name){
        return DigestUtils.md5Hex(name+ LocalDateTime.now());
    }
}
