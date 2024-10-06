package com.example.authservice.entity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Arrays;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * User Entity Class
 * This class is used to store user details in the database and is used by the Spring Security framework
 * to authenticate and authorize users
 * also to store and to manage user related information
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * this username, password, authorities are based on Spring Security UserDetails Interface
     */
    @Column(unique = true, nullable = false)
    private String username; // this is used to login ID
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String roles;

    /*
     * this is used to store the user status
     * for more information, please refer to Spring Security UserDetails Interface
     */
    @Column(nullable = false)
    private boolean isAccountNonExpired;
    @Column(nullable = false)
    private boolean isAccountNonLocked;
    @Column(nullable = false)
    private boolean isCredentialsNonExpired;
    @Column(nullable = false)
    private boolean isEnabled;

    /*
     * this infomation is customized based on the requirements
     */
    private String name;
    private String loginID;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*
     * this method is used to set the createdAt and updatedAt fields
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /*
     * these method is used to set the updatedAt field
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /*
     * these method is used to get the user details
     * for more information, please refer to Spring Security UserDetails Interface
     */
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(roles));
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Builder
    public User(String loginID, String password, String email, String phoneNumber, String name, String address, LocalDate birthDate, String username) {
        this.loginID = loginID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.roles = "ROLE_USER";
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.username = username;
    }
}
