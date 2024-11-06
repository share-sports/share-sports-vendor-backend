package org.example.sharesportsvendorbackend.auth.domain;

import java.util.Collection;
import java.util.List;

import org.example.sharesportsvendorbackend.host.domain.Host;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class AuthUserDetails implements UserDetails {
    private String hostUuid;
    private String password;
    private String email;
    private Role role;

    @Builder
    public AuthUserDetails(Host host) {
        this.hostUuid = host.getHostUuid();
        this.password = host.getPassword();
        this.email = host.getEmail();
        this.role = host.getRole();
    }

    /**
     * 단일 role을 사용할 경우 SimpleGrantedAuthority로 변환
     * 복수 role을 사용할 경우 GrantedAuthority로 변환
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.hostUuid;
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
