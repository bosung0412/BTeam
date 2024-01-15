package kr.co.ict.project.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.ict.project.security.utils.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@AllArgsConstructor
// JWT 인증을 위한 클래스이다.
// HTTP 요청에 대해서 서버가 실행이 될 때 JWT 토큰을 검증하기 위한 클래스이고
// 스프링 시큐리티 체인에서
public class JwtTokenFilter extends OncePerRequestFilter { // JwtFilter.java
  // JWT 토큰을 생성하고 검증하는 데 사용
  private JwtTokenProvider jwtTokenProvider;
  // 사용자의 상세 정보를 로드하는 데 사용, UserDetailsService 인증된 권한일 때
  // jwtTokenProvider 로 부터 토큰을 얻어서 시큐리트에 UserDetails 반환한다.
  private UserDetailsService userDetailsService;

  // 요청이 올때 doFilterInternal 필터가 동작이 되면서 요청에 포함된 JWT 토큰이 올바른지 검증하고
  // 시큐리티 필터로 인증을 넘긴다. UserDetails에 담아서
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // Get the JWT token from the Authorization header
    // 토큰 추출하기 위한 resolveToken(요청 )
    String token = jwtTokenProvider.resolveToken(request);

    // Check if the token is valid
    // 추출된 토큰이 null이 아니고 유효한지를 체크
    if (token != null && jwtTokenProvider.validateToken(token)) {
      // 유효한 토큰이 있을 경우,
      // 토큰에서 사용자 이름을 추출하여(jwtTokenProvider.getUsername(token))
      // 해당 사용자의 상세 정보를 로드
      UserDetails userDetails = userDetailsService.loadUserByUsername(
          jwtTokenProvider.getUsername(token));
      // Create an authentication object and set it in the security context
      // UsernamePasswordAuthenticationToken 객체를 생성하여 사용자의 인증 정보 담아서
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      // 생성된 인증 객체를 SecurityContextHolder에 설정하여,
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      // 이후의 요청 처리 과정에서 현재 사용자가 인증되었음을 SecurityContextHolder를 통해서 넘긴다.
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    // filterChain.doFilter(request, response)를 호출하여 요청 처리를 계속 진행한다.
    filterChain.doFilter(request, response);

  }
  /*
   * 1. JwtTokenFilter는 모든 HTTP 요청에 대해 JWT 토큰의 유효성을 검사해서 유효한 토큰이 있을 경우
   * 해당 사용자의 인증 정보를 설정하는 역할
   * 2. 사용자가 인증된 후에는 Spring Security의 다른 컴포넌트들이 이 인증 정보를 기반으로 사용자에
   * 대한 접근 제어를 수행할 수 있다.
   */
}