package org.example.sharesportsvendorbackend.auth.dto.out;

import org.example.sharesportsvendorbackend.auth.vo.out.JwtTokenResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtTokenResponseDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String hostUuid;
    private String name;

    @Builder
    public JwtTokenResponseDto(String grantType, String accessToken, String refreshToken, String hostUuid, String name) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.hostUuid = hostUuid;
        this.name = name;
    }

    public JwtTokenResponseVo toVo() {
        return JwtTokenResponseVo.builder()
                .grantType(grantType)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .hostUuid(hostUuid)
                .name(name)
                .build();
    }
}
