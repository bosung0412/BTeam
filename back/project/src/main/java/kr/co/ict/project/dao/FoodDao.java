package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.ict.project.vo.FoodVO;

@Mapper
public interface FoodDao {
    // 추천 음식 리스트
    public List<FoodVO> selectList();

    // 추천 음식 상세 정보
    public FoodVO foodDetail(int nutrient_id);
}
