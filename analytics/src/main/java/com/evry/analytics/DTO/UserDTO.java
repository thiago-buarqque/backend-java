package com.evry.analytics.DTO;

import com.evry.analytics.model.annotation.JSONField;
import com.evry.analytics.model.annotation.JSONSerializable;
import com.evry.analytics.model.annotation.PhoneNumber;
import com.evry.analytics.model.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Getter
@JSONSerializable
@Setter
public class UserDTO {

    public UserDTO() {}

    public UserDTO(User user) {
        if (user != null) {
            address = user.getAddress();
            birthday = user.getBirthday();
            email = user.getEmail();
            firstName = user.getFirstName();
            gender = user.getGender();
            id = user.getId().toString();
            lastName = user.getLastName();
            middleName = user.getMiddleName();
            phone = user.getPhone();
            role = user.getRole();
        }
    }

    @JSONField private String id;

    @JSONField private String address;

    @JSONField
    @NotNull(message = "User birthday must be provided.")
    private LocalDate birthday;

    @JSONField
    @NotBlank(message = "User e-mail must be provided.")
    private String email;

    @JSONField
    @NotBlank(message = "User first name must be provided.")
    private String firstName;

    @JSONField private String gender;

    @JSONField
    @NotBlank(message = "User last name must be provided.")
    private String lastName;

    @JSONField private String middleName;

    @JSONField
    @PhoneNumber(country = "Brazil")
    private String phone;

    @JSONField
    private String role;
}
