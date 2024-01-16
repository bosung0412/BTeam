package kr.co.ict.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String id_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
