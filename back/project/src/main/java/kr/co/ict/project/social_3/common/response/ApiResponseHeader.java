package kr.co.ict.project.social_3.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseHeader {
    private int code;
    private String message;

}// ApiResponseHeader