package kr.co.ict.project.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.co.ict.project.login.entity.CertificationEntity;


@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String>{
    
    CertificationEntity findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}
