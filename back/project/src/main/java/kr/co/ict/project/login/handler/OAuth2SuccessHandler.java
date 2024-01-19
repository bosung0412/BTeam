package kr.co.ict.project.login.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.login.entity.CustomOAuth2User;
import kr.co.ict.project.login.provider.JwtProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
// OAuth2 인증 성공 시 처리를 담당하는 클래스 SimpleUrlAuthenticationSuccessHandler - 커스텀 인증 성공
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    // 사용자가 성공적으로 OAuth2 인증을 완료했을 시 호출
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // authentication.getPrincipal()을 통해 사용자 정보를 추출
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String accessToken = oAuth2User.getAccessToken();

        logger.info("Access Token: " + accessToken);

        String userId = oAuth2User.getName();
        // 추출한 사용자 정보를 기반으로 JWT 토큰을 생성
        String token = jwtProvider.create(userId);

        // 생성한 JWT 토큰을 포함한 리다이렉트 URL을 생성하여 클라이언트로 리다이렉트
        response.sendRedirect("http://localhost:8081/auth/oauth-response/" + token + "/3600");

    }

}
