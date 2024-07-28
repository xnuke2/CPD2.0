package com.cpd.cpd2.interfaceadapter.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
@Getter
public class MinioConfig {

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.port}")
    private int port;

    @Value("${minio.secure}")
    private Boolean minioSecure;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().credentials(accessKey, secretKey)
                .endpoint(minioUrl, port, minioSecure).build();
        return minioClient;
    }
}
