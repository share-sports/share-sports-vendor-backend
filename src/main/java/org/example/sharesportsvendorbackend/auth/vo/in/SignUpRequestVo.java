package org.example.sharesportsvendorbackend.auth.vo.in;

import org.example.sharesportsvendorbackend.auth.domain.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class SignUpRequestVo {

    @Schema(description = "이메일", example = "test1234@gmail.com")
    private String email;

    @Schema(description = "비밀번호", example = "!test1234")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "역할", example = "HOST")
    private Role role;

    @Builder
    public SignUpRequestVo(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

}
