package kr.co.ict.project.login.common;

public interface  ResponseMessage {
    
    String SUCCESS = "Sucess";

    String VALIDATION_FAIL = "Validation failed";
    String DUPLICATE_ID = "Duplicate Id";

    String SIGN_IN_FAIL = "Login information mismathch";
    String CERTIFICATION_FAIL = "Certifiation failed";
    
    String DATABASE_ERROR = "Database error";
}
