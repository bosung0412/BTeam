package kr.co.ict.project.login.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kr.co.ict.project.login.common.ResponseCode;
import kr.co.ict.project.login.common.ResponseMessage;
import kr.co.ict.project.login.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class IdCheckResponseDto extends ResponseDto{
    
    // 부모 생성자 호출
    private IdCheckResponseDto(){
        super();
    }

    public static ResponseEntity<IdCheckResponseDto> success(){
        IdCheckResponseDto responseBody = new IdCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedId(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
