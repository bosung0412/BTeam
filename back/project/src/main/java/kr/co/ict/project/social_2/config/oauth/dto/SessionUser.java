package kr.co.ict.project.social_2.config.oauth.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.ict.project.social_2.domain.Users;

import java.util.ArrayList;
import java.util.Collection;

@Getter
// 사용자 세션에 저장될 정보를 담고 security에서 인증 및 권한 부여에 사용
public class SessionUser implements UserDetails {
    // 객체를 받아와서 내부필드로 설정, 사용자의 정보를 받아오기위해
    private Users user;

    public SessionUser(Users user) {
        this.user = user;
    }

    @Override
    // 사용자 정보를 반환, security에서 권한을 부여할 때 사용되는 포맷
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();

        collections.add(() -> {
            return "ROLE_" + user.getRole();
        });

        return collections;
    }

    @Override
    // 사용자의 비밀번호 반환
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    // 사용자의 이름 반환
    public String getUsername() {
        return user.getName();
    }

    // 계정의 유효성 여부인데 true를 반환하므로 항상 유효한 상태로 설정
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
