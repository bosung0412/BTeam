package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.FoodService;
import kr.co.ict.project.vo.DietVO;
import kr.co.ict.project.vo.DietinfoVO;
import kr.co.ict.project.vo.FoodVO;

@RestController
public class FoodController {
    @Autowired
    private FoodService service;

    @GetMapping("/foodlist")
    public List<FoodVO> listfood() {
        return service.selectFoodList();
    }
    @PostMapping("/foodAdd")
    public int addFood(@RequestBody FoodVO vo) {
        int res = service.insertFood(vo);
        return res;
    }
    @GetMapping("/selectfood")
    public FoodVO selectFood(@RequestParam int nutrient_id){
        return service.selectFood(nutrient_id);
    }

    @GetMapping("/selectdiet")
    public List<DietVO> selectDiet(){
        return service.selectDiet();
    }

    @GetMapping("/selectdietinfo")
    public List<DietinfoVO> selectDietInfo(int diet_id){
        return service.selectDietInfo(diet_id);
    }
    @GetMapping("/selectfoodinfo")
    public DietinfoVO selectFoodInfo(int nutrient_id){
        return service.selectFoodInfo(nutrient_id);
    }
}
