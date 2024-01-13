package kr.co.ict.project.security.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.ict.project.member.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
// @Slf4j 어노테이션: Lombok 라이브러리의 Slf4j 어노테이션을 사용하여 로깅을 위한 Logger를 자동으로 생성
@Slf4j
public class JwtTokenProvider {

    // Key key: JWT 생성 및 검증에 사용되는 비밀 키, HS512 알고리즘으로 생성
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String createToken(Authentication authentication, MemberDto member) {
        // 스프링 시큐리티에서 Authentication 객체로 부터
        // 사용자의 UserDetails를 얻어 사용자 이름을 주제(subject)로 설정하고,
        // 현재 시간과 만료 시간을 포함한 토큰을 생성
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // 사용자
                .setIssuedAt(new Date()) // new Date로 현재 시간 기반으로 생성
                .setExpiration(expiryDate) // 만료세팅
                .signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                // .signWith(SignatureAlgorithm.HS512,key) 였다.
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 생성기
    public String generateJwtToken(MemberDto member) {
        Date now = new Date();
        System.out.println(member);
        JwtBuilder builder = Jwts.builder()
                .setSubject(member.getId()) // 보통 username
                .setHeader(createHeader())
                .setClaims(createClaims(member)) // 클레임, 토큰에 포함될 정보
                .setExpiration(new Date(now.getTime() + 3600000)); // 만료일

        return builder.signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 헤더 셋팅
    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT"); // 토큰 종류설정
        header.put("alg", "HS256"); // 해시 256 사용하여 암호화
        header.put("regDate", System.currentTimeMillis()); // 생성시간
        return header;
    }

    // 토큰에 추가 정보 셋팅
    private Map<String, Object> createClaims(MemberDto member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getId()); // 로그인 id
        return claims;
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    // 해결하기 payload에 클레임 저장 X
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    // 토큰에 저장한 로그인 id값 꺼내서 반환
    public String getUsernameFromToken(String token) {
        return (String) getClaims(token).get("username");
    }

    // 토큰에 저장한 user type값 꺼내서 반환
    public int getRoleFromToken(String token) {
        return Integer.parseInt(getClaims(token).get("roles").toString());
    }

    // 제공된 JWT 토큰이 유효한지 검증하기 위해서 만든다.
    public boolean validateToken(String token) {
        // Check if the token is valid and not expired
        try {
            // Jwts.parser().setSigningKey(key).parseClaimsJws(token); 라인을 사용하여 토큰의 유효성을 검사
            // Jwts.parser().setSigningKey(key).parseClaimsJws(token); => deprecated
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            // 토큰값이 유효하면 true
            return true;
        } catch (MalformedJwtException ex) { // 토큰의 형식이 올바르지 않을 때 발생
            log.error("Invalid JWT token : 토큰의 형식이 올바르지 않음 ");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token : 토큰이 만료되었을 때 발생");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token : 지원되지 않는 토큰 유형일 때 발생");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty : 토큰이 비어있거나 null일 때 발생");
        } catch (SignatureException e) {
            log.error("there is an error with the signature of you token : 토큰의 서명이 유효하지 않을 때 발생");
        }
        // 토큰이 유효하지 않으면 false
        return false;
    }

    public String getUsername(String token) {
        // Extract the username from the JWT token
        // return Jwts.parser()
        // .setSigningKey(key)
        // .parseClaimsJws(token)
        // .getBody()
        // .getSubject();
        //// Jwts.parser().setSigningKey(key).parseClaimsJws(token)가
        // Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token) 로 변경

        // 새로운 방식으로 키 설정 및 토큰 파싱
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}