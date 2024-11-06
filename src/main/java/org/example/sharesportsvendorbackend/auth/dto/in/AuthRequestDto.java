package org.example.sharesportsvendorbackend.auth.dto.in;

import org.example.sharesportsvendorbackend.host.domain.Host;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthRequestDto {

    private String email;

// 임시 비밀번호로 비밀번호 변경

    public Host changeTemporaryPassword(PasswordEncoder passwordEncoder, Host host, String temporaryPassword) {
        return Host.builder()
                .id(host.getId())
                .email(host.getEmail())
                .password(passwordEncoder.encode(temporaryPassword))
                .hostUuid(host.getHostUuid())
                .name(host.getName())
                .role(host.getRole())
            .build();
    }

    @Builder
    public AuthRequestDto(String email) {
        this.email = email;
    }
}
