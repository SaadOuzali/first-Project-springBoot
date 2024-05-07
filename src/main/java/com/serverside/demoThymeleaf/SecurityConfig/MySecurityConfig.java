package com.serverside.demoThymeleaf.SecurityConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class MySecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Filtre filtre;

    @Autowired
    private UserDetailsService customerDetailsService;

    // in memoryauthentication we helping us to define user li bginahom yda5lo l app diali
    //whadi basic
//    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(
                User.withUsername("saad").password(passwordEncoder.encode("1234")).roles("USER") .build(),
                User.withUsername("hassan").password(passwordEncoder.encode("5678")).roles("USER","ADMIN").build()
        );

        return inMemoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/patient/auth").hasAuthority("ADMIN");
                    auth.requestMatchers("/user/test").hasAuthority("ADMIN");
                    auth.requestMatchers("/user/test3").hasRole("ADMIN");
//                    auth.requestMatchers(HttpMethod.GET,"/user/test3").hasRole("ADMIN");
                    auth.requestMatchers("/patient/delete/**").hasRole("ADMIN");
                    auth.requestMatchers("/patient/**","/h2-console/** ","/user/**").permitAll();
                    auth.anyRequest().authenticated();
                }).
                formLogin(Customizer.withDefaults())

//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .exceptionHandling(ex->ex.accessDeniedPage("/user/notauthorized"))
                .cors(c->c.disable())
                .csrf(csrf-> csrf.disable())
                .headers(h->h.disable())
                .userDetailsService(customerDetailsService)
                .addFilterBefore(filtre, UsernamePasswordAuthenticationFilter.class)

        ;
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
