package com.fpt.fitme.model;

import com.fpt.fitme.entity.appuser.AppUser;

public class AuthenticationResponse {

    private final String jwt;
    private AppUser appUser;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
