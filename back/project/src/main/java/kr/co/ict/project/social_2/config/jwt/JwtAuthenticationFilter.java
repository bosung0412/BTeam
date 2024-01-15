package kr.co.ict.project.social_2.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

// security의 filter를 사용해서 jwt토큰을 처리하는 클래스
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    // 객체를 주입받아 필터에 사용
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // http요청이 필터를 통과할 때마다 호출되는 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // resolveToken : 메서드 호출해서 http요청에서 jwt 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        // 토큰 유효성 검사 -> jwtTokenProvider를 통해
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // 토큰이 유효하면 contextholder에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 나머지는 필터 체인에 요청을 계속 전달
        chain.doFilter(request, response);
    }

    // 헤더에서 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Authorization헤더를 확인하고, 해당 헤더의 값이 "Bearer "로 시작하면 실제 토큰 값을 반환 -> Bearer뒤에 공백한칸
        // 띄워야됨
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        // 없으면 null
        return null;
    }
}
