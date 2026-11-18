package com.billing.login.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.billing.login.model.User;

public class JwtResponse {

    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private Object user;

    public JwtResponse(String token,
                       String username,
                       Collection<? extends GrantedAuthority> authorities,
                       Object user) {

        this.token = token;
        this.username = username;
        this.authorities = authorities;
        this.user = user;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

    
    // getters and setters
}