package com.springsecurity1.app1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToOne
    private AppUser appUser;

    public VerificationToken(String value, AppUser appUser) {
        this.value = value;
        this.appUser = appUser;
    }
}
