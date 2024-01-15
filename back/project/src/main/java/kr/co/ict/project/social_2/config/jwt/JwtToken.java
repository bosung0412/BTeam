package kr.co.ict.project.social_2.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {

    // 부여된 타입
    private String grantType;
    // 접근 토큰
    private String accessToken;
    // 접근 토큰이 만료되었을때 새로운 토큰을 얻기 위해 사용되는 토큰
    private String refreshToken;
}
