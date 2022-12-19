package com.evry.analytics.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@Table
public class User {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        User user = (User) object;

        return Objects.equals(id, user.id) &&
               Objects.equals(name, user.name);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String phoneNumber;

    // Useless field to test @JSONSerializer
    @Transient
    private String[] arrayTest;

}