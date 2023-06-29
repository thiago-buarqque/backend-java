package com.evry.analytics.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
public class Visitor {

    private LocalDateTime createDate;

    @Id private String id;

    private String userId;
}
