package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("wvo")
@Getter
@Setter
public class WeightVO {
    private String id; // 회원아이디
    private int currentweight; // 현재 몸무게
    private int futureweight; // 목표 몸무게
    private String wdate; // 시간
}
