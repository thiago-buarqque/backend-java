package com.evry.analytics.DTO;

import com.evry.analytics.annotations.JSONField;
import com.evry.analytics.annotations.JSONSerializable;
import com.evry.analytics.annotations.PhoneNumber;
import com.evry.analytics.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode
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

    @JSONField
    private long id;

    @NotBlank(message = "User name can not be empty.")
    @JSONField
    private String name;

    @JSONField
    @PhoneNumber(country = "Brazil", required = true)
    private String phoneNumber;

    @Transient
    @JSONField
    private String[] arrayTest;

}