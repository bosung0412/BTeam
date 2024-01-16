package kr.co.ict.project.login.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtProvider {


    @Value("${secret-key}")
    private String secretKey;

    public String create(String userId){

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
            // signwith key부분이 encoded 마지막 부분 jwt의signature부분
            .signWith(key, SignatureAlgorithm.HS256)
            //  setsubject가 payload첫부분에서 ~~
            .setSubject(userId).setIssuedAt(new Date()).setExpiration(expiredDate)
            .compact();

            return jwt;
    }

    // jwt검증
    public String validate(String jwt){
        // 결과를 받음
        String subject = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try{
            
            subject = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();

        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }

        return null;
    }
}
