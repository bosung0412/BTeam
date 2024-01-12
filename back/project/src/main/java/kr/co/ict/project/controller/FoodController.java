package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.FoodService;
import kr.co.ict.project.vo.FoodVO;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FoodController {

    @Autowired
    private FoodService service;

    // 식단 리스트 불러오기
    @GetMapping(value = "/foodlist")
    public List<FoodVO> getFoodList() {
        return service.selectFoodList();
    }

    // 식단 상세 정보
    @GetMapping(value = "/detail")
    public FoodVO getFoodDetail(@RequestParam("fno") Integer FNO) {
        return service.FoodDetail(FNO);
    }
    

    // 식단 추가
    @PostMapping("/foodAdd")
    public int addFood(@ModelAttribute FoodVO vo) {
        int res = service.insertFood(vo);
        return res;
    }
}
