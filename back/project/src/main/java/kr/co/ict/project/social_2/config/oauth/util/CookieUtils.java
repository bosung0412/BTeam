package kr.co.ict.project.social_2.config.oauth.util;

import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Base64;
import java.util.Optional;

public class CookieUtils {

    // 주어진 쿠키 이름과 일치하는 쿠키를 요청에서 찾아 Optional로 반환
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    // 주어진 이름, 값 및 최대 수명으로 설정된 새로운 쿠키를 응답에 추가
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    // 주어진 이름과 일치하는 쿠키를 찾아 값을 비우고, 최대 수명을 0으로 설정하여 삭제
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    // Base64 : 공통 ASCII 영역의 문자로만 이루어진 문자열로 바꾸는 Encoding
    // 주어진 객체를 직렬화하고 Base64로 인코딩하여 문자열로 반환
    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    // public static <T> T deserialize(Cookie cookie, Class<T> cls) {
    // return cls.cast(SerializationUtils.deserialize(
    // Base64.getUrlDecoder().decode(cookie.getValue())));
    // }
    // 주어진 쿠키를 역직렬화하고 지정된 클래스 유형으로 변환하여 반환
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        try {
            String json = new String(Base64.getUrlDecoder().decode(cookie.getValue()));
            return new ObjectMapper().readValue(json, cls);
        } catch (Exception e) {
            // 처리할 예외 발생 시 작업
            e.printStackTrace();
            return null; // 또는 예외를 던지거나 다른 적절한 조치를 취할 수 있음
        }
    }

}
