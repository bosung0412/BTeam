package kr.co.ict.project.login.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.ict.project.login.common.CertificationNumber;
import kr.co.ict.project.login.dto.request.auth.EmailCertificationRequestDto;
import kr.co.ict.project.login.dto.request.auth.IdCheckRequestDto;
import kr.co.ict.project.login.dto.response.ResponseDto;
import kr.co.ict.project.login.dto.response.auth.EmailCertificationResponseDto;
import kr.co.ict.project.login.dto.response.auth.IdCheckResponseDto;
import kr.co.ict.project.login.entity.CertificationEntity;
import kr.co.ict.project.login.provider.EmailProvider;
import kr.co.ict.project.login.repository.CertificationRepository;
import kr.co.ict.project.login.repository.UserRepository;
import kr.co.ict.project.login.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{
    
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    private final EmailProvider emailProvider;

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return IdCheckResponseDto.duplicateId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {

        try {
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

}
