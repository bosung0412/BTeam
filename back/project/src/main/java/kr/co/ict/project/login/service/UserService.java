package kr.co.ict.project.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.ict.project.login.entity.UserEntity;
import kr.co.ict.project.login.repository.UserRepository;

@Service

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 목록 조회 메서드
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    
    // 사용자 삭제 메서드
    @Transactional
    public void deleteUser(String userId) {
        // 사용자가 존재하는지 확인
        if (userRepository.existsByUserId(userId)) {
            userRepository.deleteByUserId(userId);
        } else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
        }
    }
}