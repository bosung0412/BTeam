package kr.co.ict.project.social_2.config.oauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.ict.project.social_2.config.oauth.dto.SessionUser;
import kr.co.ict.project.social_2.domain.Users;
import kr.co.ict.project.social_2.repository.UserRepository;

@Service
// 사용자의 상세 정보를 데이터베이스에 로드하고 springsecuirty에 정보 제공
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository repository;

    public PrincipalDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    // 사용자 이름이나 이메일을 받아 해당 사용자의 상세 정보를 로드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자의 정보를 데이터베이스에서 찾음
        Users principal = repository.findByEmail(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다");
                });
        // 존재하면 객체를 생성해서 반환 SessionUser는 security에서 사용하는 구현체
        return new SessionUser(principal);
    }
}
