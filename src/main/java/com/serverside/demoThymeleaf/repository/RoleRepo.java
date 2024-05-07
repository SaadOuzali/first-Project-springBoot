package com.serverside.demoThymeleaf.repository;

import com.serverside.demoThymeleaf.model.entitie.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleUser,Long> {
    RoleUser findByRole(String rolen);
}
