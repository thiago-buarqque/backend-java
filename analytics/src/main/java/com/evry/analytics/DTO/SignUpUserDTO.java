package com.evry.analytics.DTO;

import com.evry.analytics.model.annotation.JSONField;
import com.evry.analytics.model.annotation.JSONSerializable;
import com.evry.analytics.model.annotation.PhoneNumber;
import com.evry.analytics.model.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@JSONSerializable
@Setter
public class SignUpUserDTO {

    public SignUpUserDTO() {}

    public SignUpUserDTO(User user) {
        if (user != null) {
            address = user.getAddress();
            birthday = user.getBirthday();
            email = user.getEmail();
            firstName = user.getFirstName();
            gender = user.getGender();
            lastName = user.getLastName();
            middleName = user.getMiddleName();
            phone = user.getPhone();
            role = user.getRole();
        }
    }

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

    @NotBlank(message = "A password must be provided.")
    private String password;

    @JSONField
    @PhoneNumber(country = "Brazil")
    private String phone;

    @JSONField
    private String role;
}
