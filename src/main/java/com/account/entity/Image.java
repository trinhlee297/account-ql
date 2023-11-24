package com.account.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url_name")
    private String urlName;

    @OneToMany(mappedBy = "image")

    private List<AccountImage> accountImages;
}
