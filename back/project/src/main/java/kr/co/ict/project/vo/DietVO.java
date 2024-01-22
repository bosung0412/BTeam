package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("dvo")
@Setter
@Getter
public class DietVO {
    private int diet_id;
    private int member_no;
    private String created_at;
    private String img_filename;
    private String mealtime;
}
