package org.example.sharesportsvendorbackend.auth.dto.in;

import org.example.sharesportsvendorbackend.host.domain.Host;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PasswordRequestDto {

    private String password;

    public Host changePassword(PasswordEncoder passwordEncoder, Host host, PasswordRequestDto passwordRequestDto) {
        return Host.builder()
                .id(host.getId())
                .email(host.getEmail())
                .password(passwordEncoder.encode(passwordRequestDto.getPassword()))
                .hostUuid(host.getHostUuid())
                .name(host.getName())
                .role(host.getRole())
            .build();
    }

    @Builder
    public PasswordRequestDto(String password) {
        this.password = password;
    }

}
