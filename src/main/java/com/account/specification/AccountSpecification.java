package com.account.specification;

import com.account.entity.Account;
import com.account.form.AccountFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {
    public static Specification<Account> buildSpec(AccountFilterForm form) {
        return form == null ? null : (Specification<Account>) (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            var name = form.getName();
            if (StringUtils.hasText(name)) {
                String pattern = "%" + name.trim() + "%";
                Predicate hasNameLike = builder.like(root.get("name"), pattern);
                predicates.add(hasNameLike);
            }

            var email = form.getEmail();
            if (StringUtils.hasText(email)) {
                String pattern = "%" + email.trim() + "%";
                Predicate hasEmailLike = builder.like(root.get("email"), pattern);
                predicates.add(hasEmailLike);
            }


            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
