package kr.co.ict.project.login.service;

import org.springframework.http.ResponseEntity;

import kr.co.ict.project.login.dto.request.auth.EmailCertificationRequestDto;
import kr.co.ict.project.login.dto.request.auth.IdCheckRequestDto;
import kr.co.ict.project.login.dto.response.auth.EmailCertificationResponseDto;
import kr.co.ict.project.login.dto.response.auth.IdCheckResponseDto;

public interface AuthService {
    // 부모형태도 가져올 수 있게 
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
}
