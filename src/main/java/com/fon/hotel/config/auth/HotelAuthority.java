package com.fon.hotel.config.auth;

import org.springframework.security.core.GrantedAuthority;

public class HotelAuthority implements GrantedAuthority {

    private String role;

    public HotelAuthority(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
