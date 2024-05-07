package com.serverside.demoThymeleaf.repository;

import com.serverside.demoThymeleaf.model.entitie.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AppUserRepo  extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUserName(String username);
}
