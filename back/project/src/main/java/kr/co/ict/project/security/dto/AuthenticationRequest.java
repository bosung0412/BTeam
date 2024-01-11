package kr.co.ict.project.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
  // 사용자 이름을 저장
  private String username;
  // 사용자 비밀번호를 저장
  private String password;
}