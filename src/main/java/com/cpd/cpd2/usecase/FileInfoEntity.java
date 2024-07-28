package com.cpd.cpd2.usecase;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class FileInfoEntity {
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "date_of_upload")
    private LocalDateTime dateOfUpload;

    @Column(name = "storage_time")
    private LocalDateTime dateToRemove;
}
