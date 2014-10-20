package com.simbircite.demo.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
public class User implements UserDetails {
 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "confirm")
    private String confirm;
    
    public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	@Column(name = "salt")
    private String salt;
    
    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String value) {
        username = value;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String value) {
        password = value;
    }
    
    public String getSalt() {
    	return salt;
    }
    
    public void setSalt(String value) {
    	salt = value;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	Collection<SimpleGrantedAuthority> result = new HashSet<SimpleGrantedAuthority>();
    	result.add(new SimpleGrantedAuthority("user"));
        return result;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}