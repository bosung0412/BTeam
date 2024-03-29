package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("mvo")
public class MemberVO {
    private int member_no;   // 회원 번호
    private Long id;         // 회원 아이디
    private String name;     // 회원 이름
    private String password; // 비밀번호
    private String birth;    // 생년월일
    private String address;  // 주소
    private String question; // 찾기 질문
    private String answer;   // 찾기 답변
    private String phoneno;  // 휴대폰 번호
    private String email;    // 이메일
    private String gender;   // 성별
    private String disease;  // 질병
    private Integer height;  // 키
    private Integer weight;  // 몸무게
    private String role;     // 권한
    private String type;     // 타입
}