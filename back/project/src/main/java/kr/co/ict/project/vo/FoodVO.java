package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("Fvo")
@Getter
@Setter
public class FoodVO {
    private Integer FNO;                // 음식 번호
    private String FIMAGE;          // 음식 사진
    private String FNAME;           // 음식 이름
    private String FPROVINCE;       // 지방
    private String FFPROVINCE;      // 포화 지방
    private String FCHOLESTEROL;    // 콜레스테롤
    private String FSALT;           // 나트륨
    private String FPOTASIUM;       // 칼륨
    private String FCARBOHYDRATE;   // 탄수화물
    private String FDIETARYFIBER;   // 식이섬유
    private String FPROTEIN;        // 단백질
}
