package com.evry.analytics.entity;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Transient
    private Long userId;

    @NotNull
    @Transient
    private String name;
}
