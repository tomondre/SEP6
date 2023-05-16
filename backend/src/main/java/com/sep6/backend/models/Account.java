package com.sep6.backend.models;

import com.sep6.backend.security.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    private List<Review> reviews;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Token> tokens;

    @ManyToMany(mappedBy = "favouredBy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Movie> favourites;

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
