package kr.co.ict.project.social;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class KakaoLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    // access token을 얻어줌
    @GetMapping("/login/{code}")
    public HashMap<String, String> kakaoLogin(@PathVariable("code") String code) {
        System.out.println(code);
        // 토큰을 요청하여 얻음
        String kakaoToken = kakaoLoginService.requestToken(code);
        System.out.println("카카오토큰" + kakaoToken);

        // 사용자 정보를 요청하여 얻음
        HashMap<String, String> userInfo = kakaoLoginService.requestUser(kakaoToken);
        System.out.println("userInfo : " + userInfo);

        return userInfo;
    }
}
