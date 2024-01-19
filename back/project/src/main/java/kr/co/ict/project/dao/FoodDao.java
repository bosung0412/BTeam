package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.ict.project.vo.FoodVO;

@Mapper
public interface FoodDao {
    // 추천 음식 리스트
    public List<FoodVO> selectList();

    // 추천 음식 상세 정보
    public FoodVO foodDetail(int nutrient_id);

    // foodtype에 해당하는 음식 리스트 가져오기
    public List<FoodVO> selectListByType(@Param("foodtype") String foodtype);
}
