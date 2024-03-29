package kr.co.ict.project.login.common;

public interface ResponseCode {

    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATE_ID = "DI";

    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";

    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";

    // 업데이트 실패 시 응답코드
    String UPDATE_FAIL = "UF";
}
