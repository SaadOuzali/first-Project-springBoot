package com.serverside.demoThymeleaf.controller;

import com.serverside.demoThymeleaf.model.dto.AuthReq;
import com.serverside.demoThymeleaf.model.dto.UserRoleDTO;
import com.serverside.demoThymeleaf.model.entitie.AppUser;
import com.serverside.demoThymeleaf.model.entitie.RoleUser;
import com.serverside.demoThymeleaf.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@NoArgsConstructor
public class SecurityController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/notauthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }

    @PostMapping("/create")
    @ResponseBody
    public AppUser createuser(@RequestBody AppUser appUser){
        AppUser user=this.userServiceImpl.addUser(appUser);
        return user;
    }

    @PostMapping("/role/create")
    @ResponseBody
    public RoleUser creatrole(@RequestBody RoleUser roleUser){
        RoleUser role=this.userServiceImpl.addRole(roleUser);
        return role;
    }

    @PostMapping("/addusertogroup")
    @ResponseBody
    public String add_user_to_group(@RequestBody UserRoleDTO userRoleDTO){
        System.out.println(userRoleDTO.getRoleName());
        System.out.println(userRoleDTO.getUserName());
        String msg=this.userServiceImpl.addRoleToUser(userRoleDTO.getRoleName(),userRoleDTO.getUserName());
        return msg;
    }

    @GetMapping("/test")
    @ResponseBody
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Authentication test(Authentication authentication){
        return authentication;
    }

    @GetMapping("/test2")
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    public String test2(){
        return "sir 3alah";
    }





    @GetMapping("/test3")
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    public String test3(){
        return "5ask tkon role admin";
    }

    @GetMapping("/t")
    @ResponseBody
    public Authentication aut(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.isAuthenticated());
        return authentication;
    }

    @GetMapping("/jwt")
    public String j(){
        return "safi kon hani";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody AuthReq authReq){
        System.out.println("d5al");
        System.out.println("before ");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
                authReq.getEmail(),authReq.getPassword());

        System.out.println(authenticationToken.isAuthenticated());
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authReq.getEmail(),authReq.getPassword())
        );


SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());;
        return "login success";
    }

}
