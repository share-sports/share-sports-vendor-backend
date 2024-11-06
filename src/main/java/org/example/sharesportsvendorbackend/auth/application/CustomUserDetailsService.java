package org.example.sharesportsvendorbackend.auth.application;

import org.example.sharesportsvendorbackend.auth.domain.AuthUserDetails;
import org.example.sharesportsvendorbackend.global.common.response.BaseResponseStatus;
import org.example.sharesportsvendorbackend.global.error.BaseException;
import org.example.sharesportsvendorbackend.host.domain.Host;
import org.example.sharesportsvendorbackend.host.infrastructure.HostRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final HostRepository hostRepository;

    @Override
    public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return hostRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));
    }

    private AuthUserDetails createUserDetails(Host host) {
        return AuthUserDetails.builder()
                .host(host)
                .build();
    }
}
