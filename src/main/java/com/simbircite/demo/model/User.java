package com.simbircite.demo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.simbircite.demo.util.DateUtil;

/**
 * Модель данных
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
 
    @NotBlank(message = "{validation.user.name.NotBlank}")
    @Size(max=100, message = "{validation.user.name.Size}")
    @Column(name = "name", nullable = false)
    private String name;
 
    @NotBlank(message = "{validation.user.email.NotBlank}")
    @Email(message = "{validation.user.email.InvalidFormat}")
    @Size(max=50, message = "{validation.user.email.Size}")
    @Column(name = "email", nullable = false)
    private String email;
 
    @NotNull(message = "{validation.user.birth.NotNull}")
    @Past(message = "{validation.user.birth.Past}")
    @Column(name = "birth", nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    private DateTime birth;
    
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getBirth() {
        return birth;
    }

    public void setBirth(DateTime birth) {
        this.birth = birth;
    }
    
    public String getBirthString() {
        return DateUtil.format(birth);
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
