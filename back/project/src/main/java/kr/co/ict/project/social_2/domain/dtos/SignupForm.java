package kr.co.ict.project.social_2.domain.dtos;

import kr.co.ict.project.social_2.domain.Users;
import kr.co.ict.project.social_2.domain.eums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupForm {
    // null이거나 공백문자만으로 이루어져있으면 안됨
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    // 이메일 주소를 나타내고 이메일 주소의 유효성 확인
    @Email(message = "올바른 형식의 이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    // 암호화된 비밀번호를 받아서 엔터티를 생성
    public Users toEntity(String encPwd) {
        return Users.builder()
                .name(name)
                .email(email)
                .password(encPwd)
                .role(Role.USER)
                .build();
    }
}