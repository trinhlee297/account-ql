package com.account.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @OneToMany(mappedBy = "account")

    private List<AccountImage> accountImages;

}
