package kr.co.ict.project.config.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// JPA에서 사용할 엔티티 이름을 지정
@Entity
@Getter
@Setter
// ToString 메소드 자동 생성
@ToString
// 파라미터가 없는 기본 생성자를 생성
@NoArgsConstructor
// 어노테이션은 모든 필드 값을 파라미터로 받는 생성자 생성
@AllArgsConstructor
public class Members {

    // 유니크한 DB의 컬럼과 맵핑
    @Id
    // GeneratedValue - 엔터티의 기본 키를 자동 생성, 그뒤는 테이블 기본 키 필드를 자동으로 증가하는 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
