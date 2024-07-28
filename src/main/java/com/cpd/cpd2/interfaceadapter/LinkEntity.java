package com.cpd.cpd2.interfaceadapter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "links")
public class LinkEntity {

    @Id
    @Column(name = "id")
    String key;

    @Column(name = "file_id")
    String fileID;
}
