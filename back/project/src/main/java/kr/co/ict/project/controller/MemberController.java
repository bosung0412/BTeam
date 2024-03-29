package kr.co.ict.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.MemberService;
import kr.co.ict.project.vo.MemberVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/auth")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/memberAdd")
    public int addMember(@RequestBody MemberVO vo) {
        int res = memberService.addMember(vo);
        return res;
    }
    @GetMapping("/getmemberinfo")
    public MemberVO getMemberInfo(@RequestParam String user_id) {
        return memberService.getMemberInfo(user_id);
    }
    @GetMapping("/getmemheight")
    public MemberVO getMemHeight(@RequestParam("user_id") String user_id) {
        return memberService.getMemHeight(user_id);
    }
}