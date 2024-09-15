package com.nextstep.flyway.pac.migrate.service;



import com.nextstep.flyway.pac.migrate.model.UserInfoModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


//The UserDetailInfo class represents the authenticated user's details (like username, password,
//        and roles) in Spring Security. During authentication, it's used to load user
//        information from the database and verify credentials. If successful, it helps generate
//        a JWT token to secure further API requests. This class also ensures the user's account is
//        valid and active for access control.
public class UserDetailInfo  implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailInfo(UserInfoModel member) {
        username = member.getUsername();
        password = member.getPassword();
        authorities = Arrays.stream(member.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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