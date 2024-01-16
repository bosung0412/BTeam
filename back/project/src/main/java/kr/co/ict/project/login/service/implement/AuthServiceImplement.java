package kr.co.ict.project.login.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.ict.project.login.dto.request.auth.IdCheckRequestDto;
import kr.co.ict.project.login.dto.response.ResponseDto;
import kr.co.ict.project.login.dto.response.auth.IdCheckResponseDto;
import kr.co.ict.project.login.repository.UserRepository;
import kr.co.ict.project.login.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{
    
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return IdCheckResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }
    
}
