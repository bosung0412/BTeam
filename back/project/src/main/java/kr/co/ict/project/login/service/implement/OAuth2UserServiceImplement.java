package kr.co.ict.project.login.service.implement;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.ict.project.login.entity.CustomOAuth2User;
import kr.co.ict.project.login.entity.UserEntity;
import kr.co.ict.project.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        // try {
        // System.out.println(new
        // ObjectMapper().writeValueAsString(oauth2User.getAttributes()));
        // } catch (Exception exception) {
        // exception.printStackTrace();
        // }

        UserEntity userEntity = null;
        String userId = null;
        String email = "email@email.com";

        if (oauthClientName.equals("kakao")) {
            userId = "kakao_" + oauth2User.getAttributes().get("id");
            userEntity = new UserEntity(userId, "email", "kakao");
        }

        if (oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oauth2User.getAttributes().get("response");
            userId = "naver_" + responseMap.get("id").substring(0, 14);
            email = responseMap.get("email");
            userEntity = new UserEntity(userId, email, "naver");
        }

        userRepository.save(userEntity);

        return new CustomOAuth2User(userId);

    }
}
