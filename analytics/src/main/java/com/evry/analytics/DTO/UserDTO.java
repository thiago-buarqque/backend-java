package com.evry.analytics.DTO;

import com.evry.analytics.annotations.JSONField;
import com.evry.analytics.annotations.JSONSerializable;
import com.evry.analytics.annotations.PhoneNumber;
import com.evry.analytics.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@JSONSerializable
@Setter
public class UserDTO {

    public UserDTO() {
    }

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        phoneNumber = user.getPhoneNumber();
        arrayTest = user.getArrayTest();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        UserDTO user = (UserDTO) object;

        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name);
    }

    @JSONField
    private long id;

    @NotBlank(message = "User name can not be empty.")
    @JSONField
    private String name;

    @JSONField
    @PhoneNumber(country = "Brazil")
    private String phoneNumber;

    @Transient
    @JSONField
    private String[] arrayTest;

}