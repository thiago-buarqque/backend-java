package com.evry.analytics.entity;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table
public class User {

    private String address;

    @NotNull private LocalDate birthday;

    private LocalDateTime createDate;

    @NotNull private String email;

    @NotNull private String firstName;

    private String gender;

    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull private String lastName;

    private String middleName;

    @NotNull private String password;

    private String phone;
}
