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

    public List<DietVO> selectDiet(){
        return dao.selectDiet();
    }

    public List<DietinfoVO> selectDietInfo(int diet_id){
        return dao.selectDietInfo(diet_id);
    }
    public DietinfoVO selectFoodInfo(int nutrient_id){
        return dao.selectFoodInfo(nutrient_id);
    }

    // 음식 상세 보기
    public FoodVO FoodDetail(int nutrient_id) {
        return dao.foodDetail(nutrient_id);
    }

    // 추천 식단
    public List<FoodVO> getRandomMeals() {
        return dao.selectRandomMeals();
    }


}