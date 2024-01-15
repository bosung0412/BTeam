package kr.co.ict.project.social_3.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class OauthDTO {
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakao_client_id;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakao_client_secret;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String kakao_token_uri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakao_user_info_uri;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakao_redirect_uri;

    @Value("${kakao.logout-uri}")
    private String kakao_logout_uri;

    private String access_token;

}// OauthDTO