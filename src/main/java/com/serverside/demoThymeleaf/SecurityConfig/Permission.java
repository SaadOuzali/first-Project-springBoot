package com.serverside.demoThymeleaf.SecurityConfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read");
//    ADMIN_CREATE("admin:create");
//    ADMIN_DELETE("admin:delete");



    private final String permission;
}
