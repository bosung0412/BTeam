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
    // public List<WeightVO> find();

    public WeightVO weightuser(int member_no);

    public WeightVO weightfuture(int member_no);

    public int addweight(WeightVO vo);

    public int upweight(WeightVO vo);

    public WeightVO weightlast(int member_no);

    public List<WeightVO> getWeightLogs(int member_no);

}