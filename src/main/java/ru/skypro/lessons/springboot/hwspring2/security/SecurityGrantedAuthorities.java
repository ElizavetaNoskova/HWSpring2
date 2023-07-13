package ru.skypro.lessons.springboot.hwspring2.security;

import org.springframework.security.core.GrantedAuthority;
import ru.skypro.lessons.springboot.hwspring2.security.UserRole;

public class SecurityGrantedAuthorities implements GrantedAuthority {
    private String role;

    public SecurityGrantedAuthorities(UserRole userRole) {
        this.role = userRole.getRoles();
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}