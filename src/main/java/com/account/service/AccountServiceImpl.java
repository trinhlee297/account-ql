package com.account.service;

import com.account.dto.AccountDto;
import com.account.entity.Account;
import com.account.form.AccountCreateForm;
import com.account.form.AccountFilterForm;
import com.account.form.AccountUpdateForm;
import com.account.reponsitory.AccountReponsitory;
import com.account.specification.AccountSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountReponsitory accountReponsitory;

    private final ModelMapper modelMapper;


    @Override
    public Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable) {
        var spec = AccountSpecification.buildSpec(form);
        return accountReponsitory.findAll(spec, pageable)
                .map(account -> modelMapper.map(account, AccountDto.class).withSelfRel());

    }

    @Override
    public AccountDto findById(Long id) {
        return accountReponsitory.findById(id)
                .map(account -> modelMapper.map(account, AccountDto.class).withSelfRel())
                .orElse(null);

    }

    @Override
    public AccountDto create(AccountCreateForm form) {
        var account = modelMapper.map(form, Account.class);
        var savedPost = accountReponsitory.save(account);
        return modelMapper.map(savedPost, AccountDto.class);
    }

    @Override
    public AccountDto update(Long id, AccountUpdateForm form) {
        var account = accountReponsitory.findById(id)
                .orElse(null);
        modelMapper.map(form, account);
        var savedAccount = accountReponsitory.save(account);
        return modelMapper.map(savedAccount, AccountDto.class);
    }

    @Override
    public void deleteById(Long id) {
        accountReponsitory.deleteById(id);
    }

    @Override
    public List<Account> getTheListAccount() {
        return accountReponsitory.findAll();
    }

    public void saveAccountsToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Account> accounts = ExcelUploadService.getAccountsDataFromExcel(file.getInputStream());
                this.accountReponsitory.saveAll(accounts);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    @Override
    public List<Account> getAccounts() {
        return accountReponsitory.findAll();
    }

    @Override
    public void addAccount(Account account) {
        accountReponsitory.save(account);

    }

}
