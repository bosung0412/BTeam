package kr.co.ict.project.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ict.project.login.entity.FAQBoard;

public interface FAQBoardRepository extends JpaRepository<FAQBoard, Long> {
    
}
