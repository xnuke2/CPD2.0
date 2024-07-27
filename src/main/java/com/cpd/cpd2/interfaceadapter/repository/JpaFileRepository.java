package com.cpd.cpd2.interfaceadapter.repository;

import com.cpd.cpd2.usecase.FileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFileRepository extends JpaRepository<FileInfoEntity, String> {

}
