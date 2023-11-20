package com.account.controller;

import com.account.dto.AccountDto;
import com.account.entity.Account;
import com.account.form.AccountCreateForm;
import com.account.form.AccountFilterForm;
import com.account.form.AccountUpdateForm;
import com.account.service.AccountService;
import com.account.util.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@Validated
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/v1/accounts")
    public Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable) {
        return accountService.findAll(form, pageable);
    }

    @GetMapping("/api/v1/accounts/{id}")
    public AccountDto findById(@PathVariable("id") Long id) {
        return accountService.findById(id);
    }

    @PostMapping("/api/v1/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody @Valid AccountCreateForm form) {
        return accountService.create(form);
    }

    @PutMapping("/api/v1/accounts/{id}")
    public AccountDto update(@PathVariable("id") Long id, @RequestBody @Valid AccountUpdateForm form) {
        return accountService.update(id, form);
    }

    @DeleteMapping("/api/v1/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        accountService.deleteById(id);
    }

    @GetMapping("/accounts/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=accounts_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Account> listAccounts = accountService.getTheListAccount();

        ExcelGenerator excelExporter = new ExcelGenerator(listAccounts);

        excelExporter.generateExcelFile(response);
    }

    @PostMapping("/upload-accounts-data")
    public ResponseEntity<?> uploadAccountData(@RequestParam ("file") MultipartFile file){
        this.accountService.saveAccountsToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Accounts data uploaded and saved to database successfully"));
    }

//    @PostMapping("/accounts/import")
//    public void importacount(@RequestParam MultipartFile file) throws IOException {
//        File
//    }
}
