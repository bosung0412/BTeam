package kr.co.ict.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.FoodDao;
import kr.co.ict.project.vo.FoodVO;

@Service
public class FoodService {
    @Autowired
    private FoodDao dao;

    // 리스트 보기
    public List<FoodVO> selectFoodList() {
        return dao.selectList();
    }

    // 음식 상세 보기
    public FoodVO FoodDetail(int nutrient_id) {
        return dao.foodDetail(nutrient_id);
    }
}