package kr.co.ict.project.social_2.config.oauth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import kr.co.ict.project.social_2.domain.Users;
import kr.co.ict.project.social_2.domain.eums.Role;

@Getter
@Builder
// 소셜 로그인에서 얻은 사용자 정보를 담는 클래스
public class OAuthAttributes {
    // 소셜 로그인으로부터 받은 모든 사용자 정보를 담는 맵
    private Map<String, Object> attributes;
    // 소셜 로그인에서 사용자 이름을 식별하는 키
    private String nameAttributeKey;
    // 사용자 이름
    private String name;
    // 사용자 이메일 주소
    private String email;
    // 소셜 로그인을 제공하는 서비스
    private String provider;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
            Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .provider("Google")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .provider("Naver")
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // 카카오에서 제공하는 소셜 로그인의 응답 정보에서 필요한 정보를 추출하여 객체를 생성하는 정적메서드
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> account = (Map<String, Object>) response.get("profile");

        return OAuthAttributes.builder()
                .name((String) account.get("nickname"))
                .email((String) response.get("email"))
                .provider("Kakao")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .role(Role.USER)
                .build();
    }
}
