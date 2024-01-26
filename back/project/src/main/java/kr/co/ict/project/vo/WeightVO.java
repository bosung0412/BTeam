package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("wvo")
@Getter
@Setter
public class WeightVO {
    private int w_no; // 회원아이디
    private int member_no;
    private double currentweight; // 현재 몸무게
    private double futureweight; // 목표 몸무게
    private String wdate; // 시간
    private double weight;
    private double hist_currentweight;
    private double hist_futureweight;
    private String change_date;
    private double lead_futureweight;
    private double previous_futureweight;
}