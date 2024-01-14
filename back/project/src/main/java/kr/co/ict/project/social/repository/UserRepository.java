package kr.co.ict.project.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ict.project.social.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    boolean existsUsersByEmail(String email);
}