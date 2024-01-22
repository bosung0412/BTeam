package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.DietVO;
import kr.co.ict.project.vo.DietinfoVO;
import kr.co.ict.project.vo.FoodVO;

@Mapper
public interface FoodDao {
    public List<FoodVO> selectList();
    public int addFood(FoodVO vo);
    public FoodVO selectFood(int nutrient_id);

    public List<DietVO> selectDiet();

    public List<DietinfoVO> selectDietInfo(int diet_id);
    public DietinfoVO selectFoodInfo(int nutrient_id);
}
