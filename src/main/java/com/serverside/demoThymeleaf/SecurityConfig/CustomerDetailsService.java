package com.serverside.demoThymeleaf.SecurityConfig;

import com.serverside.demoThymeleaf.model.entitie.AppUser;
import com.serverside.demoThymeleaf.model.entitie.RoleUser;
import com.serverside.demoThymeleaf.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private UserServiceImpl userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user=this.userServiceImpl.findbyusername(username);
        System.out.println("fhad fonction password     "+user.get().getPassword());
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found with this name: "+username);
        }
        String userName=user.get().getUserName();
        String password=user.get().getPassword();
        List<GrantedAuthority> roles= new ArrayList<>();
       roles=user.get().getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());

//        String[] roles=user.get().getRoles().stream().map(role -> role.getRole()).toArray(String[]::new);
        return new CustomerUserDetails(userName,password,roles);
    }
}
