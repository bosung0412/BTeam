package kr.co.ict.project.social_2.config.oauth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.ict.project.social_2.config.oauth.dto.OAuthAttributes;
import kr.co.ict.project.social_2.domain.Users;
import kr.co.ict.project.social_2.repository.UserRepository;

import java.util.Collections;

@Service
// 소셜 로그인 사용 및 사용자 정보를 가져오고 저장하는 역할
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    // 사용자 정보를 가져오는 역할
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // DefaultOAuth2UserService 을 사용하여 사용자 정보를 가져옴
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 소셜 로그인 registrationid와 사용자 이름 속성을 추출
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        // 소셜 로그인에서 받은 사용자 정보를 가공
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());

        // saveOrUpdate클래스를 사용해서 DB 사용자 정보를 저장 or update를 함
        Users user = saveOrUpdate(attributes);

        // Spring Security가 사용하는 형식으로 사용자 정보를 반환
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Users saveOrUpdate(OAuthAttributes attributes) {
        //// 이미 등록된 사용자가 있는지 조회하고, 있으면 해당 사용자 엔티티를 업데이트하고, 없으면 새로운 사용자 엔티티를 생성
        Users user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getProvider()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
