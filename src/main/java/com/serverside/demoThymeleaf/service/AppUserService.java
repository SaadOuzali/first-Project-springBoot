package com.serverside.demoThymeleaf.service;

import com.serverside.demoThymeleaf.model.entitie.AppUser;
import com.serverside.demoThymeleaf.model.entitie.RoleUser;
import com.serverside.demoThymeleaf.repository.AppUserRepo;

public interface AppUserService {
    AppUser addUser(AppUser user);

    RoleUser addRole(RoleUser roleUser);

    void deleteUser(Long userId);


}
