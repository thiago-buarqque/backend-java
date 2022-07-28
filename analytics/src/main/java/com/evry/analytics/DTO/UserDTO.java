package com.evry.analytics.DTO;

import com.evry.analytics.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
    }

    private long userId;

    private String name;
}
