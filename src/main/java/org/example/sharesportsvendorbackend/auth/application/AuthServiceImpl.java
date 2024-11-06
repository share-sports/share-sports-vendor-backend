package org.example.sharesportsvendorbackend.auth.application;

import org.example.sharesportsvendorbackend.auth.domain.AuthUserDetails;
import org.example.sharesportsvendorbackend.auth.dto.in.AuthCodeRequestDto;
import org.example.sharesportsvendorbackend.auth.dto.in.AuthRequestDto;
import org.example.sharesportsvendorbackend.auth.dto.in.PasswordRequestDto;
import org.example.sharesportsvendorbackend.auth.dto.in.SignInRequestDto;
import org.example.sharesportsvendorbackend.auth.dto.in.SignUpRequestDto;
import org.example.sharesportsvendorbackend.auth.dto.out.JwtTokenResponseDto;
import org.example.sharesportsvendorbackend.global.common.UuidGenerator;
import org.example.sharesportsvendorbackend.global.common.jwt.JwtTokenProvider;
import org.example.sharesportsvendorbackend.global.common.response.BaseResponseStatus;
import org.example.sharesportsvendorbackend.global.error.BaseException;
import org.example.sharesportsvendorbackend.auth.vo.in.RefreshTokenRequestDto;
import org.example.sharesportsvendorbackend.host.domain.Host;
import org.example.sharesportsvendorbackend.host.infrastructure.HostRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final HostRepository hostRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    /**
     * AuthServiceImpl
     * 1. 회원가입
     * 2. 로그인
     * 3. 로그아웃
     */

    /**
     * 1. 회원가입
     * Save user
     *
     * @param signUpRequestDto return void
     */
    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {

        log.info("signUpRequestDto : {}", signUpRequestDto);
        hostRepository.save(signUpRequestDto.toEntity(passwordEncoder));
    }

    /**
     * 2. 로그인
     * Authenticate user
     *
     * @param signInRequestDto return signInResponseDto
     */
    @Override
    @Transactional
    public JwtTokenResponseDto signIn(SignInRequestDto signInRequestDto) {

        log.info("jwtTokenRequestDto : {}", signInRequestDto);
        Host host = hostRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN)
        );

        try {
            JwtTokenResponseDto jwtTokenResponseDto = createToken(authenticate(host, signInRequestDto.getPassword()));
            jwtTokenResponseDto.setName(host.getName());
            log.info("token : {}", jwtTokenResponseDto);

            return jwtTokenResponseDto;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }

    }

    // 임시 비밀번호 보내는 메서드
    public void sendPasswordResetEmail(AuthRequestDto authRequestDto) {
        Host host = hostRepository.findByEmail(authRequestDto.getEmail()).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

        // 임시 비밀번호로 password 변경
        String temporaryPassword = UuidGenerator.generateTemporaryPassword();
        hostRepository.save(authRequestDto.changeTemporaryPassword(passwordEncoder, host, temporaryPassword));

        // 변경 후 임시비밀번호를 알려주는 email 발송을 위한 email token 생성
        String token = jwtTokenProvider.buildEmailToken(authRequestDto.getEmail());

        // 임시 비밀번호 인증 이메일 보내기
        emailService.sendTemporaryPasswordEmail(authRequestDto.getEmail(), temporaryPassword, token);
    }

    // 인증코드 보내는 메서드
    public void sendAuthCodeEmail(HttpSession session) {

        String token = jwtTokenProvider.buildEmailToken((String) session.getAttribute("email"));
        emailService.sendAuthCodeEmail((String) session.getAttribute("email"), (String) session.getAttribute("authCode"), token);
    }

    // 인증코드 검증 메서드
    public void verifyAuthCode(AuthCodeRequestDto authCodeRequestDto, HttpSession session) {
        String authCode = (String) session.getAttribute("authCode");
        if (authCode == null || !authCode.equals(authCodeRequestDto.getAuthCode())) {
            throw new BaseException(BaseResponseStatus.AUTH_CODE_INVALID);
        }
    }

    // 비밀번호 변경 메서드
    public void changePassword(PasswordRequestDto passwordRequestDto, String memberUuid) {
        Host host = hostRepository.findByHostUuid(memberUuid).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));
        hostRepository.save(passwordRequestDto.changePassword(passwordEncoder, host, passwordRequestDto));
    }


    private JwtTokenResponseDto createToken(Authentication authentication) {
        return jwtTokenProvider.generateToken(authentication);
    }


    private Authentication authenticate(Host host, String inputPassword) {
        AuthUserDetails authUserDetail = new AuthUserDetails(host);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUserDetail.getUsername(),
                        inputPassword,
                        authUserDetail.getAuthorities()
                )
        );
    }

    /**
     * 3. RefreshAccessToken
     *
     * @param refreshTokenRequestDto return JwtTokenResponseDto
     */
    @Override
    public JwtTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        return jwtTokenProvider.refreshAccessToken(refreshTokenRequestDto.getRefreshToken());
    }

    /**
     * 4. Sign out
     *
     * @param authUserDetails return void
     */
    @Override
    public void signOut(AuthUserDetails authUserDetails) {
        jwtTokenProvider.deleteRefreshToken(authUserDetails.getUsername());
    }

}
