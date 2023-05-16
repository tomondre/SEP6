package com.sep6.backend.models;

import com.sep6.backend.security.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class Account implements UserDetails
{
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String country;
    private String profilePictureUrl;
    private Date dateOfBirth;
    private String gender;
    private boolean isEnabled;
    @OneToMany(mappedBy = "account")
    @Column(name = "reviews_id")
    private List<Review> reviews;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "favouredByAccounts")
    private List<Movie> favourites;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return isEnabled;
    }
}
