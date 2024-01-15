package kr.co.ict.project.social_2.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
// jwt토큰을 생성, 검증 및 사용자 인증을 담당하는 클래스
public class JwtTokenProvider {

    private final Key key;

    // properties에 설정된 jwt 시크릿 키를 주입 -> security바뀌면서 key를 자동으로 랜덤 생성하는걸로 바꿔야될듯?
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }

    // 객체에서 사용자의 권한 정보를 추출하여 JWT Access Token을 생성
    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Access Token 생성(사용자이름, 권한 정보 등 설정)
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // jwt access token을 파싱, 객체반환
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        // 최종적인 authentication 객체 생성
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // jwt 유효성 검사
    public boolean validateToken(String token) {
        try {
            // paserBuilder를 사용하여 토큰을 파싱하고, 시크릿키 설정
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
            // 예외처리
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    // jwt access token을파싱하고 claims 객체에 반환
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
            // 토큰이 만료될경우 예외처리 및 예외에서claims 객체를 얻어서 반환
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
