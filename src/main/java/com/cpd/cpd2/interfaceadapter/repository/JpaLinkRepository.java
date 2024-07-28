package com.cpd.cpd2.interfaceadapter.repository;

import com.cpd.cpd2.interfaceadapter.LinkEntity;
import com.cpd.cpd2.usecase.FileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLinkRepository extends JpaRepository<LinkEntity, String> {
}
