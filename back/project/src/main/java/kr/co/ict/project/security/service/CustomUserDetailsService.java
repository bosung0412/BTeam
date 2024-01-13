package kr.co.ict.project.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.ict.project.security.repo.UserRepository;

// UserDetailsService 인터페이스를 구현한 클래스
// UserDetails 객체를 로드하는 역할을 수행
// UserDetails 객체는 사용자의 인증 및 권한 부여 과정에서 사용된다.
// 비밀번호 체크, 권한 ROLE에대한 체크를 담당하도록 구현!
@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  // UserDetails: 스프링 시큐리티에서 입력이후에 사용자의 인증, 권한을 부여할 때 사용하는 객체
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      System.out.println("Optional<User> =>" + userRepository.findByUsername(username));
      return userRepository.findByUsername(username)
          .orElseThrow(() -> new Exception("user Not found "));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}