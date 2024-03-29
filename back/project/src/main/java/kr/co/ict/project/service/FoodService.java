package kr.co.ict.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.FoodDao;
import kr.co.ict.project.vo.DietVO;
import kr.co.ict.project.vo.DietinfoVO;
import kr.co.ict.project.vo.FoodVO;

@Service
public class FoodService {

    @Autowired
    private FoodDao dao;

    // 리스트 보기
    public List<FoodVO> selectFoodList() {
        return dao.selectList();
    }
    public int insertFood(FoodVO vo) {
        return dao.addFood(vo);
    }
    public FoodVO selectFood(int nutrient_id){
        return dao.selectFood(nutrient_id);
    }

    public List<DietVO> selectDiet(String user_id){ // Calendar 식단 리스트와 식사타임별 총 칼로리
        return dao.selectDiet(user_id);
    }

    public List<DietinfoVO> selectDietInfo(int diet_id){ // Calendar 식사 타임별 음식 정보 및 영양 정보
        return dao.selectDietInfo(diet_id);
    }
    public DietinfoVO selectFoodInfo(int nutrient_id, int diet_id){ // Calendar 음식별 영양 정보
        return dao.selectFoodInfo(nutrient_id, diet_id);
    }
    
    public FoodVO FoodDetail(int nutrient_id) { // 음식 상세 보기
        return dao.foodDetail(nutrient_id);
    }
    public List<FoodVO> getRandomMeals() { // 추천 식단
        return dao.selectRandomMeals();
    }

    public List<DietVO> totalCalbyId(String user_id){ // main 해당 회원의 총 칼로리
        return dao.totalCalbyId(user_id);
    }
}