package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.FoodService;
import kr.co.ict.project.vo.DietVO;
import kr.co.ict.project.vo.DietinfoVO;
import kr.co.ict.project.vo.FoodVO;

@RestController
@RequestMapping("/api/v1/auth")
public class FoodController {

    @Autowired
    private FoodService service;

    // 식단 리스트 불러오기
    @GetMapping("/foodlist")
    public List<FoodVO> getFoodList() {
        return service.selectFoodList();
    }
    @PostMapping("/foodAdd")
    public int addFood(@RequestBody FoodVO vo) {
        int res = service.insertFood(vo);
        return res;
    }
    @GetMapping("/selectfood")
    public FoodVO selectFood(@RequestParam("nutrient_id") int nutrient_id){
        return service.selectFood(nutrient_id);
    }

    @GetMapping("/selectdiet") // Calendar 식단 리스트와 식사타임별 총 칼로리
    public List<DietVO> selectDiet(@RequestParam("user_id") String user_id){
        return service.selectDiet(user_id);
    }

    @GetMapping("/selectdietinfo")  // Calendar 식사 타임별 음식 정보 및 영양 정보
    public List<DietinfoVO> selectDietInfo(@RequestParam("diet_id") int diet_id){
        return service.selectDietInfo(diet_id);
    }
    @GetMapping("/selectfoodinfo") // Calendar 음식별 영양 정보
    public DietinfoVO selectFoodInfo(@RequestParam int nutrient_id, @RequestParam int diet_id){ 
        return service.selectFoodInfo(nutrient_id, diet_id);
    }

    // 식단 상세 정보
    @GetMapping(value = "/detail")
    public FoodVO getFoodDetail(@RequestParam("nutrient_id") int nutrient_id) {
        FoodVO vo = service.FoodDetail(nutrient_id);
        return vo;
    }

    @GetMapping("/randomMeals")
    public List<FoodVO> getRandomMeals() {
        return service.getRandomMeals();
    }

    @GetMapping("/totalCalbyId")
    public List<DietVO> totalCalbyId(@RequestParam("user_id") String user_id){ // main 해당 회원의 총 칼로리
        return service.totalCalbyId(user_id);
    }
}
