package kr.co.ict.project.social_3.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialDTO {
    private String access_token;
    private String refresh_token;
    private String token_type;
}// SocialDTO
