package kr.co.ict.project.security.utils;

//요청이 들어 올때 잘못된 토큰으로 들어 오면 인증 오류를 응답하기 위한 클래스 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//Spring Security에서 인증 실패 했을 때 처리하는 클래스이고,
//추상 클래스인 BasicAuthenticationEntryPoint 를 상속 받아서 재정의 해서 사용한다.
// BasicAuthenticationEntryPoint 이클래스는 기본적인 HTTP 기반 인증 실패 처리를 구현한 클래스 클래스다.
//Spring의 컴포넌트임
@Component
public class JwtAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        // HTTP 응답 상태를 401 (Unauthorized)로 설정 해놓고 인증되지 않은 요청값으로 상태값을 응답으로 설정한다.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 마임타입을 json으로 응답 시켜놓는다.
        response.setContentType("application/json");
        // 에러 메세지를 보낸다.
        response.getWriter().write("{ \"message\": \"" + authException.getMessage() + "\" }");
    }

    // Bean의 속성이 설정된 후에 호출
    @Override
    public void afterPropertiesSet() {
        // setRealmName("JWT Authentication")을 호출하여, 인증 영역의 이름을 "JWT Authentication"으로
        // 설정
        setRealmName("JWT Authentication");
        super.afterPropertiesSet();
    }
}