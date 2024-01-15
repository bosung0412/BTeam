package kr.co.ict.project.social_2.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import kr.co.ict.project.social_2.config.jwt.JwtTokenProvider;
import kr.co.ict.project.social_2.domain.Users;
import kr.co.ict.project.social_2.domain.dtos.SignupForm;
import kr.co.ict.project.social_2.repository.UserRepository;

@Service
@Transactional
public class UserService {

    // 비밀번호를 안전하게 저장하기 위해 사용되는 Spring Security
    private final BCryptPasswordEncoder encoder;
    // 사용자 정보에 접근하기 위한 Spring Data JPA의 리포지토리
    private final UserRepository repository;
    // 스프링 시큐리티에서 인증을 처리하기 위한 빌더
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    // JWT 토큰을 생성하고 검증하는데 사용되는 클래스
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(BCryptPasswordEncoder encoder, UserRepository repository,
            AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.encoder = encoder;
        this.repository = repository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(String email, String password) {
        // UsernamePasswordAuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
                password);

        // 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 검증된 인증 정보로 JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    public Long signup(SignupForm signupForm) {
        // checkEmailExists 통해 존재하는 이메일인지 확인
        boolean check = checkEmailExists(signupForm.getEmail());

        if (check) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }

        String encPwd = encoder.encode(signupForm.getPassword());

        Users user = repository.save(signupForm.toEntity(encPwd));

        if (user != null) {
            return user.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public boolean checkEmailExists(String email) {
        return repository.existsUsersByEmail(email);
    }

}
