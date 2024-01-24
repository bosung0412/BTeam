package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("pvo")
@Setter
@Getter
public class BoardprevVO {
    private int ono;
    private int prevno;
    private String prevname;
    private String prevdate;
}