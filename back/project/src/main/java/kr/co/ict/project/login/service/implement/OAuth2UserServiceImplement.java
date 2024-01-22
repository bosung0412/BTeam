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
    // OAuth2 인증 과정에서 사용자 정보 로드
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        // loaduser에 request던지면 결과를 받을 수 있음
        OAuth2User oauth2User = super.loadUser(request);

        // 액세스 토큰 추출
        String accessToken = request.getAccessToken().getTokenValue();

        // 클라이언트 이름을 받아옴
        String oauthClientName = request.getClientRegistration().getClientName();

        // try {
        // System.out.println(new
        // ObjectMapper().writeValueAsString(oauth2User.getAttributes()));
        // } catch (Exception exception) {
        // exception.printStackTrace();
        // }

        // return oauth2User

            

        UserEntity userEntity = null;
        String userId = null;
        String email = "email@email.com";
        // clientname : kakao
        if (oauthClientName.equals("kakao")) {
            userId = "kakao_" + oauth2User.getAttributes().get("id");
            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) {
                userEntity = new UserEntity(userId, "email", "kakao");
                userRepository.save(userEntity);
            }
        } else if (oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oauth2User.getAttributes().get("response");
            userId = "naver_" + responseMap.get("id").substring(0, 14);
            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) {
                email = responseMap.get("email");
                userEntity = new UserEntity(userId, "email", "naver");
                userRepository.save(userEntity);
            }
        }

        return new CustomOAuth2User(userId, accessToken);

    }
}
