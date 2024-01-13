package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ict.project.vo.FoodVO;

@Mapper
public interface FoodDao {
    // 추천 식단 리스트
    public List<FoodVO> selectList();

    // 추천 식단 상세 정보
    public FoodVO foodDetail(int fno);
    
    // 추천 식단 추가
    public int addFood(FoodVO vo);
}
