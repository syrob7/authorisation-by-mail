package com.springsecurity1.app1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdminAuthorizationToken {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String value;

        @OneToOne
        private AppUser appUser;

        public AdminAuthorizationToken(String value, AppUser appUser) {
            this.value = value;
            this.appUser = appUser;
        }
}
