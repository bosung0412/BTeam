package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.FoodVO;

@Mapper
public interface FoodDao {
    public List<FoodVO> selectList();

    public int addFood(FoodVO vo);
}
