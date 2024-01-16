package kr.co.ict.project.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ict.project.login.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

    boolean existsByUserId(String userId);

    // 아이디 찾기
    UserEntity findByUserId(String userId);
}
