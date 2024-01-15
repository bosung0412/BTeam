package kr.co.ict.project.social_2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.ict.project.social_2.domain.dtos.SignupForm;
import kr.co.ict.project.social_2.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // client로 받은 로그인 정보를 사용해서 service.login 메서드 호출 및 토큰을 얻음
    @PostMapping("/login")
    public ResponseEntity<String> loginSuccess(@RequestBody Map<String, String> loginForm) {
        String token = service.login(loginForm.get("username"), loginForm.get("password"));
        return ResponseEntity.ok(token);
    }

    // 회원가입 처리 메소드
    // 회원가입 정보를 받아서 새로운 사용자 등록 및 id 반환
    @PostMapping("/signup")
    public Long signup(@RequestBody SignupForm signupForm) {
        return service.signup(signupForm);
    }

    // 특정 이메일 주소의 중복 여부를 확인하는 메서드
    @GetMapping("/signup/check/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        // 이메일이 등록되었는지 여부 판단
        return ResponseEntity.ok(service.checkEmailExists(email));
    }

}
