package kr.co.ict.project.login.entity;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
// springsecurity의 OAuth2User 인테페이스를 구현하는 class
public class CustomOAuth2User implements OAuth2User {
    // OAuth2 사용자의 식별자를 저장하는 필드
    private String userId;
    private String accessToken;

    @Override
    // getAttributes - OAuth2 사용자의 속성을 반환
    public Map<String, Object> getAttributes() {
        return null;
    }

    // 액세스 토큰 반환 메서드
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    // getAuthorities - OAuth2 사용자 권한 목록을 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    // getName - OAuth2 사용자의 이름을 반환
    public String getName() {
        return this.userId;
    }

}
