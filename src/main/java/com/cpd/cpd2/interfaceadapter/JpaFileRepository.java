package com.cpd.cpd2.interfaceadapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface JpaFileRepository extends JpaRepository<FileInfoMapper, String> {

}
