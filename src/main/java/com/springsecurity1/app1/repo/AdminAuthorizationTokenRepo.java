package com.springsecurity1.app1.repo;

import com.springsecurity1.app1.entity.AdminAuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminAuthorizationTokenRepo  extends JpaRepository<AdminAuthorizationToken, Long> {

    Optional<AdminAuthorizationToken> findByValue(String token);

    Long deleteByValue(String token);
}
