package com.account.validation;

import com.account.reponsitory.AccountReponsitory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountIdExistsValidator implements ConstraintValidator<AccountIdExists, Long > {
    private final AccountReponsitory accountReponsitory;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return accountReponsitory.existsById(id);
    }
}
