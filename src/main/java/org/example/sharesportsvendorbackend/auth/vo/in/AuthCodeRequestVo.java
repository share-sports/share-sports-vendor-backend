package org.example.sharesportsvendorbackend.auth.vo.in;

import org.example.sharesportsvendorbackend.auth.dto.in.AuthCodeRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeRequestVo {

private String authCode;


    public static AuthCodeRequestDto toDto(AuthCodeRequestVo authCodeRequestVo) {
        return AuthCodeRequestDto.builder()
                .authCode(authCodeRequestVo.authCode)
                .build();
    }

}
