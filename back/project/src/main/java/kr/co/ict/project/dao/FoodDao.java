package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.DietVO;
import kr.co.ict.project.vo.DietinfoVO;
import kr.co.ict.project.vo.FoodVO;

@Mapper
public interface FoodDao {
    // 추천 음식 리스트
    public List<FoodVO> selectList();
    public int addFood(FoodVO vo);
    public FoodVO selectFood(int nutrient_id);

    public List<DietVO> selectDiet(String user_id); // Calendar 식단 리스트와 식사타임별 총 칼로리

    public List<DietinfoVO> selectDietInfo(int diet_id); // Calendar 식사 타임별 음식 정보 및 영양 정보
    public DietinfoVO selectFoodInfo(int nutrient_id, int diet_id); // Calendar 음식별 영양 정보

    // 추천 음식 상세 정보
    public FoodVO foodDetail(int nutrient_id);

    // 랜덤 음식 선택
    public List<FoodVO> selectRandomMeals();

    public List<DietVO> totalCalbyId(String user_id); // main 해당 회원의 총 칼로리
}
