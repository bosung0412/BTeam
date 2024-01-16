package kr.co.ict.project.login.service;

import org.springframework.http.ResponseEntity;

import kr.co.ict.project.login.dto.request.auth.CheckCertificationRequestDto;
import kr.co.ict.project.login.dto.request.auth.EmailCertificationRequestDto;
import kr.co.ict.project.login.dto.request.auth.IdCheckRequestDto;
import kr.co.ict.project.login.dto.request.auth.SignInRequestDto;
import kr.co.ict.project.login.dto.request.auth.SignUpRequestDto;
import kr.co.ict.project.login.dto.response.auth.CheckCertificationResponseDto;
import kr.co.ict.project.login.dto.response.auth.EmailCertificationResponseDto;
import kr.co.ict.project.login.dto.response.auth.IdCheckResponseDto;
import kr.co.ict.project.login.dto.response.auth.SignInResponseDto;
import kr.co.ict.project.login.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    // 부모형태도 가져올 수 있게 
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);

}
