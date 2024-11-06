package org.example.sharesportsvendorbackend.auth.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JwtTokenResponseVo {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String hostUuid;
    private String name;

    @Builder
    public JwtTokenResponseVo(String grantType, String accessToken, String refreshToken, String hostUuid, String name) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.hostUuid = hostUuid;
        this.name = name;
    }
}
