package com.springsecurity1.app1.repo;

import com.springsecurity1.app1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

   Optional<AppUser> findAllByUsername(String username);
}
