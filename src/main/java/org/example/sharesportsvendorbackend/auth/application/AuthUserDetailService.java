package org.example.sharesportsvendorbackend.auth.application;

import org.example.sharesportsvendorbackend.auth.domain.AuthUserDetails;
import org.example.sharesportsvendorbackend.host.infrastructure.HostRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final HostRepository hostRepository;
    @Override
    public UserDetails loadUserByUsername(String hostUuid) throws UsernameNotFoundException {
        return new AuthUserDetails(hostRepository.findByHostUuid(hostUuid).orElseThrow(() -> new UsernameNotFoundException(hostUuid)));
    }

}

