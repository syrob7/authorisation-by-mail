package com.springsecurity1.app1.repo;

import com.springsecurity1.app1.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByValue(String value);

    Long deleteByValue(String value);
}
