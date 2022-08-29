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
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

        return Objects.equals(userId, user.userId) &&
               Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Transient
    private Long userId;

    @NotBlank(message = "User name can not be empty.")
    @Transient
    private String name;
}
