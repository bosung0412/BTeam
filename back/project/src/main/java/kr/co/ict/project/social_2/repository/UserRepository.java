package kr.co.ict.project.social_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ict.project.social_2.domain.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> { // long 나중에 id string으로 바꾸면 바꾸기
    // 이메일 주소와 비밀번호를 이용해 사용자를 찾는 역할
    Users findByEmailAndPassword(String email, String password);

    // 이메일 주소를 이용해 사용자를 찾는 역할
    Optional<Users> findByEmail(String email);

    // 이메일 주소에 해당하는 사용자가 존재하는지 여부를 확인하는 역할
    boolean existsUsersByEmail(String email);
}
