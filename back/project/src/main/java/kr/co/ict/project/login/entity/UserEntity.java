package kr.co.ict.project.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ict.project.login.dto.request.auth.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_no_sequence")
    @Id
    private Long memberNo;
    private String userId;
    private String name;
    private String password;
    private String birth;
    private String address;
    private String question;
    private String answer;
    private String phoneno;
    private String email;
    private String gender;
    private String disease;
    private int height;
    private int weight;
    private String type;
    private String role;

    // SignUpRequestDto 객체로부터 UserEntity 생성 - 회원가입 목적
    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.birth = dto.getBirth();
        this.address = dto.getAddress();
        this.question = dto.getQuestion();
        this.answer = dto.getAnswer();
        this.phoneno = dto.getPhoneno();
        this.gender = dto.getGender();
        this.disease = dto.getDisease();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
        this.type = "app";
        this.role = "ROLE_USER";
    }

    public UserEntity(String userId, String email, String type) {
        this.userId = userId;
        this.password = "Passw0rd";
        this.email = email;
        this.type = type;
        this.role = "ROLE_USER";
    }

}
