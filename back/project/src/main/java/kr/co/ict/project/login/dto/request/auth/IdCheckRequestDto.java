package kr.co.ict.project.login.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {
    // 필수값이여서 not blank
    @NotBlank
    private String id;
}
