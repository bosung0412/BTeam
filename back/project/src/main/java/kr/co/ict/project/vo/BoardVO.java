package kr.co.ict.project.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("bvo")
@Setter
@Getter
public class BoardVO {
    private int ono;
    private String id;
    private String oname;
    private String ocontent;
    private String oregdate;
    private String ocategory;
}
