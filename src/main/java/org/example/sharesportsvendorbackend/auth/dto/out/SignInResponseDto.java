package org.example.sharesportsvendorbackend.auth.dto.out;

import org.example.sharesportsvendorbackend.auth.vo.out.SignInResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

    private String accessToken;
    private String name;
    private String hostUuid;

    @Builder
    public SignInResponseDto(String accessToken, String name, String hostUuid) {
        this.accessToken = accessToken;
        this.name = name;
        this.hostUuid = hostUuid;
    }

    public SignInResponseVo toVo() {
        return SignInResponseVo.builder()
                .accessToken(accessToken)
                .name(name)
                .uuid(hostUuid)
                .build();
    }
}
