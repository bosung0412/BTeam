package kr.co.ict.project.social_2.config.oauth;

import com.nimbusds.oauth2.sdk.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.social_2.config.oauth.util.CookieUtils;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
// oauth 인증요청 정보를 쿠키를 통해 저장하고 로드하는 클래스
public class HttpCookieOAuth2AuthorizationRequestRepository
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int cookieExpireSeconds = 180;

    @Override
    // 현재 요청에서 oauth 2.0 인증 요청 정보를 로드
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        // OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME 쿠키를 읽어오고 해당 쿠키가 존재하면 그값을 역직렬화해서
        // request객체로 반환
        return CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    // 인증 요청 정보를 저장
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request,
            HttpServletResponse response) {
        // OAuth2AuthorizationRequest가 null이면 관련된 쿠키를 삭제하고 메서드를 종료합니다.
        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
            return;
        }

        // null이 아니면 해당 정보를 쿠키로 만들어서 브라우저에 저장
        CookieUtils.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
                CookieUtils.serialize(authorizationRequest), cookieExpireSeconds);
        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        // 로그인 이후 리디렉션을 위한 URI도 쿠키에 저장
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, cookieExpireSeconds);
        }
    }

    // 현재 요청에서 oauth 인증 요청 정보를 로드
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    // 현재 요청에서 OAuth 2.0 인증 요청과 리디렉션 URI를 나타내는 쿠키를 삭제
    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request,
            HttpServletResponse response) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAuthorizationRequest'");
    }
}
