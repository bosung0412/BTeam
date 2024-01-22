package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("wvo")
@Getter
@Setter
public class WeightVO {
    private int member_no; // 회원번호
    private String currentweight; // 현재 몸무게
    private String futureweight; // 목표 몸무게
    private String wdate; // 시간
}
