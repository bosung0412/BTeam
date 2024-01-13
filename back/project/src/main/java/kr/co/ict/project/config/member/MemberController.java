package kr.co.ict.project.config.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.ict.project.security.utils.JwtTokenProvider;

@RestController
// 모든 도메인, 모든 요청방식에 대해 허용
@CrossOrigin
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService service;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 로그인
    @PostMapping("/login")
    public Map login(String id, String pwd, HttpSession session) {
        // key값은 문자열이고 value값이 token으로 들어가서
        // 문자열을 반환받을지 숫자를 반환받을지 모르므로 object타입으로 반환
        HashMap<String, Object> map = new HashMap<>();
        boolean flag = false;
        // service단에 getMember메서드로 회원정보 조회
        MemberDto dto = service.getMember(id);
        // dto가 null이 아니고 pwd가 일치하면 로그인 성공
        if (dto != null && pwd.equals(dto.getPwd())) {
            // 토큰생성(jwt)
            String token = jwtTokenProvider.createToken(dto);
            String loginId = dto.getId();
            // 로그인에 성공하면 true로 변경
            flag = true;
            map.put("token", token);
            System.out.println("로그인의 토큰 " + token);
            map.put("loginId", loginId);
            session.setAttribute("loginId", loginId);
            System.out.println("loginId 아이디 : " + loginId);

        }
        // map에 flag가 성공인지 아닌지를 확인 후 map에 반환
        map.put("flag", flag);
        return map;
    }
}
