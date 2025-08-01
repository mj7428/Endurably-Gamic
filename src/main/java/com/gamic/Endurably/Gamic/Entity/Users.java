package com.gamic.Endurably.Gamic.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table

public class Users implements UserDetails{

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.toUpperCase()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.isEnabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @Column
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Column
    private String role;
    @Column
    private String avatar;
    @Column
    private String token;
    @Column
    private boolean isEnabled = true;

    @OneToMany(mappedBy = "submittedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BaseLayout> submittedLayouts = new ArrayList<>();

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
        }
    public String getName(){
        return name;
        }
    public void setName(String name){
        this.name = name;
        }
    public String getEmail(){
        return email;
        }
    public void setEmail(String email){
        this.email = email;
        }
    public String getPassword(){
                return password;
                }
    public void setPassword(String password){
                this.password = password;
                }
    public String getRole(){
        return role;
        }
    public void setRole(String role){
        this.role = role;
        }
    public String getAvatar(){
        return avatar;
        }
    public void setAvatar(String avatar){
        this.avatar = avatar;
        }
    public String getToken(){
        return token;
        }
    public void setToken(String token){
        this.token = token;
    }
    public Boolean getEnabled(){
        return isEnabled;
    }
    public void setEnabled(Boolean isEnabled){
        this.isEnabled = isEnabled;
    }

    public List<BaseLayout> getSubmittedLayouts() {
        return submittedLayouts;
    }
    public void addBaseLayout(BaseLayout layout) {
        this.submittedLayouts.add(layout);
        layout.setSubmittedBy(this); 
    }
    public void removeBaseLayout(BaseLayout layout) {
        this.submittedLayouts.remove(layout);
        layout.setSubmittedBy(null);
    }
}
