package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.WeightVO;

// mapper.xml
// namespace = "kr.co.ict.project.dao.WeightDao"
// MyBatis에 사용되는 추상메서드
@Mapper
public interface WeightDao {
    // mapper.xml에 추상메서드 이름 => id="find"
    public List<WeightVO> find();

    public int addWeight(WeightVO vo);

    void upWeight(WeightVO vo);

}
