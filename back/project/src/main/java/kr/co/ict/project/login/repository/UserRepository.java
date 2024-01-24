package kr.co.ict.project.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ict.project.login.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // JpaRepository<Entity, datatype>=>datatype은 기본키 id의 데이터 타입을 뜻함

    // 사용자 아이디가 db에 존지하는지에 대한 여부
    boolean existsByUserId(String userId);

    // 주어진 사용자 아이디로 UserEntity를 조회하는 메서드
    UserEntity findByUserId(String userId);
}
