package com.account.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AccountCreateForm {

    @NotBlank(message = "Account name must not be blank")
    @Length(max = 255, message = "Account name have maximum of 255 character")
    private String name;

    @Email(message = "The Account email must contain @ character.")
    @NotBlank(message = "The Account email must not be blank")
    @Length(max = 255, message = "The Account email have maximum of 255 character")
    private String email;

    @NotBlank(message = "The Account level must not be blank")
    private String level;

    @NotBlank(message = "The Account avatar must not be blank")
    private String avatar;
}
