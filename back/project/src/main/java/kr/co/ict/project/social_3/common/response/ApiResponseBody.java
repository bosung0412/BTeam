package kr.co.ict.project.social_3.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [공통] API Response 결과의 반환 값을 관리
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponseBody<T> {
    // API 응답 결과 Response
    private T result;

    // API 응답 코드 Response
    private int resultCode;

    // API 응답 코드 Message
    private String resultMsg;

    @Builder
    public ApiResponseBody(final T result, final int resultCode, final String resultMsg) {
        this.result = result;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }// ApiResponseBody

}// ApiResponseBody