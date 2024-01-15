package kr.co.ict.project.social_2.config.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.social_2.config.jwt.JwtTokenProvider;
import kr.co.ict.project.social_2.config.oauth.util.CookieUtils;

import static kr.co.ict.project.social_2.config.oauth.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.util.Optional;

@Component
// oauth 인증 과정에서 인증이 성공한 경우의 처리를 담당하는 핸들러
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;

    // 인증 요청 정보를 쿠키를 통해 저장하고 로드하는데 사용
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider tokenProvider,
            HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.tokenProvider = tokenProvider;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    // 성공시 이 메서드 호출
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        // 현재 요청에서 관련된 인증 속성 및 쿠키를 지운다
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        // 쿠키를 통해 리디렉션 URI를 읽어옴
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        // 리다렉션 URL이 없으면 기본 대상 URL로 설정
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        // JWT 토큰을 생성하고, 리디렉션 URI에 토큰을 쿼리 파라미터로 추가한 최종 리디렉션 대상 URL을 생성
        String token = tokenProvider.generateToken(authentication);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    // 부모 클래스의 인증과 관련된 속성을 지움
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        // 인증 요청과 리디렉션 URI를 나타내는 쿠키를 삭제
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

}
