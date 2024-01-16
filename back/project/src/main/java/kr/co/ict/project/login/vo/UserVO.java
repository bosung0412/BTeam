package kr.co.ict.project.login.vo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
 User 클래스의 인스턴스는 사용자의 로그인 요청을 처리할 때 사용
사용자가 로그인을 시도하면, 시스템은 User 인스턴스를 생성하여 사용자의 정보를 로드하고, 
이 정보를 바탕으로 사용자의 인증과 권한 부여를 수행
 * 
 */
@Alias("uvo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserVO implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name; // 이름
  private String email; // 성
  private String username; // 아이디
  private String password; // 비밀번호

  @Enumerated(EnumType.STRING)
  private Role role;

  public UserVO(String name, String email, String username, String password, Role role) {
    this.name = name;
    this.email = email;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // role.name() 권한을 가져와서 , 스프링 시큐리티 타입의 Authority로 변환 해준다.
    // ADMIN -> 스프링에서 이미 설정되어 있는 ROLE의 이름으로 변경해서 돌려준다. ROLE_ADMIN
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  // 사용자의 식별값, 아이디 식별 할때 사용
  @Override
  public String getUsername() {
    return this.username;
  }

  // 만료 상태
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // 잠김 상태
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 비밀번호 만료 여부 , true만료 안됨
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정이 활성화 되었는지 여부, true는 활성화
  @Override
  public boolean isEnabled() {
    return true;
  }
}