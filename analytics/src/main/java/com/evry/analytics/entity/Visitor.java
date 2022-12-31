package com.evry.analytics.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table
public class Visitor {

    private LocalDateTime createDate;

    @Id
    private String id;

    private String userId;

}
