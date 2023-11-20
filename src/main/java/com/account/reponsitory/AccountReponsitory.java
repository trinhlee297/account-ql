package com.account.reponsitory;

import com.account.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountReponsitory extends
        JpaRepository<Account, Long>,
        JpaSpecificationExecutor<Account> {
    Page<Account> findByEmailContainsAndLevel(String name, String email, String level, Pageable pageable);
}

