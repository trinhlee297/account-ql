package com.account.service;

import com.account.dto.AccountDto;
import com.account.entity.Account;
import com.account.form.AccountCreateForm;
import com.account.form.AccountFilterForm;
import com.account.form.AccountUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@Transactional
public interface AccountService {
    Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable);
    AccountDto findById(Long id);
    AccountDto create(AccountCreateForm form);
    AccountDto update(Long id, AccountUpdateForm form);
    void deleteById(Long id);
    void addAccount(Account account);
    List<Account>getTheListAccount();
    List<Account> getAccounts();
    void saveAccountsToDatabase(MultipartFile file);

}

