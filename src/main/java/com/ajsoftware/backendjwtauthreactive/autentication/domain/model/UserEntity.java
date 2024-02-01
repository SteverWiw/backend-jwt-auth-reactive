package com.ajsoftware.backendjwtauthreactive.autentication.domain.model;




import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("ajscore.user")
public class UserEntity implements UserDetails {

    @Id
    private Long id;

    @Column("username")
    private String userName;

    @Column("password")

    private String password;
    @Column("status")
    private String status;

    @Column("idrole")
    private Long idRole;

    @Column("idperson")
    private Long idPerson;

    @Transient
    private RoleEntity role;

    @Transient
    private PersonEntity person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(role)
                .map(r -> List.of(new SimpleGrantedAuthority(r.getRoleName())))
                .orElse(Collections.emptyList());
    }


    @Override
    public String getUsername() {
        return userName;
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
        return Objects.equals(status, "S");
    }
}
