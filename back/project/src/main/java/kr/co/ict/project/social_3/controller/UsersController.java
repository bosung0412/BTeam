package kr.co.ict.project.social_3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.ict.project.social_3.DTO.UsersDTO;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/spring/users")
public class UsersController {

    private final UsersServiceImpl usersService;

    @PostMapping("/security/join")
    public UsersDTO createUser(@RequestBody UsersDTO usersDTO) {
        if (usersService.insert(usersDTO) != 1) {
            usersDTO.setMsg("회원 가입 성공");
        } else {
            usersDTO.setMsg("회원 가입 실패");
        } // else
        return usersDTO;
    }// createUser

    @RequestMapping("/{social}/login")
    public UsersDTO socialLogin(@PathVariable String social, @RequestParam Map map, UsersDTO usersDTO) {
        if (social.equals("kakao")) {
            System.out.println("Kakao 인가 코드 확인: " + map.get("code"));
        } else if (social.equals("naver")) {
            System.out.println("Naver 인가 코드 확인: " + map.get("code"));
            System.out.println("Naver 인가 State 확인: " + map.get("state"));
        } else if (social.equals("google")) {
            System.out.println("Google 인가 코드 확인: " + map.get("code"));
        } // else if

        map.put("social", social);
        map.put(social, usersService.getSocialLoginToken(map));

        map.put("user", social);
        usersDTO = usersService.getSocialUserInfo(map);

        Optional<UsersDTO> user = usersService.selectOne(usersDTO);

        if (user.isPresent()) {
            usersDTO = user.get();
            usersDTO.setMsg(social);
        } // if

        return usersDTO;

    }// socialLogin

    @GetMapping("/{social}/logout")
    public ResponseEntity<String> socialLogout(@PathVariable String social, @RequestParam Map map) {
        map.put("social", social);
        map.put("logout", social);
        return usersService.socialLogout(map);
    }// socialLogout

    @GetMapping("/security/info")
    public UsersDTO getUserInfo(@RequestParam int unum, UsersDTO usersDTO) {
        usersDTO.setUnum(unum);
        return usersService.selectOne(usersDTO).get();
    }// getUserInfo

}// UsersController