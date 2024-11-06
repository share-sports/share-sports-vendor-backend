package org.example.sharesportsvendorbackend.auth.dto.in;

import org.example.sharesportsvendorbackend.auth.domain.Role;
import org.example.sharesportsvendorbackend.auth.vo.in.SignUpRequestVo;
import org.example.sharesportsvendorbackend.global.common.UuidGenerator;
import org.example.sharesportsvendorbackend.host.domain.Host;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String password;
    private String name;
    private Role role;

    public Host toEntity(PasswordEncoder passwordEncoder) {
        return Host.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .hostUuid(UuidGenerator.generateHostUuid())
                .name(name)
                .role(role)
                .build();
    }

    public static SignUpRequestDto from(SignUpRequestVo signUpRequestVo) {
        return SignUpRequestDto.builder()
                .email(signUpRequestVo.getEmail())
                .password(signUpRequestVo.getPassword())
                .name(signUpRequestVo.getName())
                .role(signUpRequestVo.getRole())
                .build();
    }
}
