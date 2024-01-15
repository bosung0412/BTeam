package kr.co.ict.project.kakaologin.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String id;
    private String name;
    private String pwd;
    private String birth;
    private String address;
    private String question;
    private String answer;
    private String phoneno;
    private String email;
    private String gender;
    // 나중에 height, weight 데이터타입 바꿔야됨
    private String height;
    private String weight;
}
