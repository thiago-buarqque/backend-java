package com.evry.analytics.DTO;

import com.evry.analytics.annotation.JSONField;
import com.evry.analytics.annotation.JSONSerializable;
import com.evry.analytics.annotation.PhoneNumber;
import com.evry.analytics.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Transient;
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
            createDate = user.getCreateDate();
            email = user.getEmail();
            firstName = user.getFirstName();
            gender = user.getGender();
            id = user.getId().toString();
            lastName = user.getLastName();
            middleName = user.getMiddleName();
            phone = user.getPhone();
        }
    }

    @JSONField private String id;

    @JSONField private String address;

    @JSONField
    @NotNull(message = "User birthday must be provided.")
    private LocalDate birthday;

    @JSONField private LocalDateTime createDate;

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

    @NotBlank(message = "A password must be provided.")
    private String password;

    @JSONField
    @PhoneNumber(country = "Brazil")
    private String phone;
}
