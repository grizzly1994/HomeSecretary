package com.simbircite.secretary.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
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
    
    @Column(name = "username", nullable = false, unique = true)
    @NotNull(message = "{validation.empty}")
    @NotBlank(message = "{validation.empty}")
    @Email(message = "{validation.email}")
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull(message = "{validation.empty}")
    @NotBlank(message = "{validation.empty}")
    private String password;
    
    @Column(name = "salt", nullable = false)
    private String salt;
    
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    
    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

	/* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getId()
	 */
	public int getId() {
        return id;
    }

    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#setId(int)
	 */
    public void setId(int value) {
        id = value;
    }

    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getUsername()
	 */
    @Override
    public String getUsername() {
        return username;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#setUsername(java.lang.String)
	 */
    public void setUsername(String value) {
        username = value;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getPassword()
	 */
    @Override
    public String getPassword() {
        return password;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#setPassword(java.lang.String)
	 */
    public void setPassword(String value) {
        password = value;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getEmail()
	 */
    public String getEmail() {
    	return username;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getSalt()
	 */
    public String getSalt() {
    	return salt;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#setSalt(java.lang.String)
	 */
    public void setSalt(String value) {
    	salt = value;
    }
    
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getCode()
	 */
    public String getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#setCode(java.lang.String)
	 */
	public void setCode(String value) {
		this.code = value;
	}
	
    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#isConfirmed()
	 */
    public boolean isConfirmed() {
		return confirmed;
	}

	/* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#setConfirmed(boolean)
	 */
	public void setConfirmed(boolean value) {
		this.confirmed = value;
	}

	/* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#getAuthorities()
	 */
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	Collection<SimpleGrantedAuthority> result = new HashSet<SimpleGrantedAuthority>();
    	result.add(new SimpleGrantedAuthority("user"));
        return result;
    }

    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#isAccountNonExpired()
	 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#isAccountNonLocked()
	 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#isCredentialsNonExpired()
	 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* (non-Javadoc)
	 * @see com.simbircite.secretary.impl.model.User#isEnabled()
	 */
    @Override
    public boolean isEnabled() {
        return true;
    }
}