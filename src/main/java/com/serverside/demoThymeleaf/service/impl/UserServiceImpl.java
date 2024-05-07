package com.serverside.demoThymeleaf.service.impl;


import com.serverside.demoThymeleaf.model.entitie.AppUser;
import com.serverside.demoThymeleaf.model.entitie.RoleUser;
import com.serverside.demoThymeleaf.repository.AppUserRepo;
import com.serverside.demoThymeleaf.repository.RoleRepo;
import com.serverside.demoThymeleaf.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
public class UserServiceImpl implements AppUserService {


    private AppUserRepo appUserRepo;

    private RoleRepo roleRepo;

        private PasswordEncoder passwordEncoder;
    @Autowired
    UserServiceImpl(AppUserRepo appUserRepo,RoleRepo roleRepo,PasswordEncoder passwordEncoder){
        this.roleRepo=roleRepo;
        this.appUserRepo=appUserRepo;
        this.passwordEncoder=passwordEncoder;
    }

//    UserServiceImpl(AppUserRepo appUserRepo){
//        this.appUserRepo=appUserRepo;
//    }


    @Override
    public AppUser addUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.appUserRepo.save(user);
    }

    @Override
    public RoleUser addRole(RoleUser roleUser) {
      RoleUser role=  this.roleRepo.save(roleUser);
      return role;
    }

    public String addRoleToUser(String roleName,String userName){
        RoleUser roleUser=this.roleRepo.findByRole(roleName);
        AppUser appUser=this.appUserRepo.findByUserName(userName).get();
        appUser.getRoles().add(roleUser);

        return null;
    }

    public Optional<AppUser> findbyusername(String username){
        Optional<AppUser> user=this.appUserRepo.findByUserName(username);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
