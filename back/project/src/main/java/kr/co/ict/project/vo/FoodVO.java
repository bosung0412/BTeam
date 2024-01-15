package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("Fvo")
@Setter
@Getter
public class FoodVO {
    private int fno;            // 음식 번호
    private String fname;           // 음식 이름
    private String fprovince;       // 지방
    private String ffprovince;      // 포화 지방
    private String fcholesterol;    // 콜레스테롤
    private String fsalt;           // 나트륨
    private String fpotasium;       // 칼륨
    private String fcarbohydrate;   // 탄수화물
    private String fdietaryfiber;   // 식이섬유
    private String fprotein;        // 단백질
    private String tocal;           // 총 칼로리
    private String files;          // 음식 사진
}
