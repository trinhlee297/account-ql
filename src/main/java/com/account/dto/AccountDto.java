package com.account.dto;

import com.account.controller.AccountController;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
public class AccountDto extends RepresentationModel<AccountDto> {
    @Positive(message = "ID phải là số dương") // cấu hình validate là chỉ dc số dương
    private Long id;


    private String name;

    private String email;

    private String level;

    private String avatar;

    public AccountDto withSelfRel(){
        var controller = methodOn(AccountController.class);
        var link = linkTo(controller.findById(id)).withSelfRel();
        return add(link);
    }

}
