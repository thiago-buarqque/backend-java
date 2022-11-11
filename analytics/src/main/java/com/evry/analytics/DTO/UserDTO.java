package com.evry.analytics.DTO;

import com.evry.analytics.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
public class UserDTO {

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        UserDTO user = (UserDTO) object;

        return Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name);
    }

    private long userId;

    @NotBlank(message = "User name can not be empty.")
    private String name;

}