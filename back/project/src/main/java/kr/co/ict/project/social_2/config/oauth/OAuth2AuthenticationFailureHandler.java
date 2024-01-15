package kr.co.ict.project.social_2.config.oauth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.social_2.config.oauth.util.CookieUtils;

import static kr.co.ict.project.social_2.config.oauth.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;

@Component
// oauth인증과정에서 인증 실패 시 처리를 담당하는 핸들러
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    public OAuth2AuthenticationFailureHandler(
            HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    // 인증에 실패하면 이 메서드 출력
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // 현재 요청에서 리디렉션 URI를 가져오려고 시도
        String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                // 리디렉션 URI가 없으면 기본적으로 "/"로 설정
                .orElse(("/"));

        // 디렉션 URI에 현재 인증 실패에 관한 오류 메시지를 추가
        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();

        // 인증 요청과 리디렉션 URI를 나타내는 쿠키를 삭제
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
