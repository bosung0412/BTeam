package kr.co.ict.project.login.service.implement;


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
            userEntity = new UserEntity(userId, "email", "kakao");
        }
            // 데이터베이스에서 사용자를 찾습니다.
            UserEntity existingUser = userRepository.findByUserId(userId);

        // 사용자가 존재하지 않는 경우에만 저장합니다.
            if (existingUser == null) {
                userRepository.save(userEntity);
        }

        return new CustomOAuth2User(userId, accessToken);

    }
}
