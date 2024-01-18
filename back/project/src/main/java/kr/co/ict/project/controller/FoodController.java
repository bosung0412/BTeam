package kr.co.ict.project.controller;

import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.FoodService;
import kr.co.ict.project.vo.FoodVO;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FoodController {

    @Autowired
    private FoodService service;

    // 정적 리소스를 배포할 수 있도록 경로 지정
    private final static String imageDirectory = Paths.get("").toAbsolutePath()
            .resolve("back/project/src/main/resources/static/images").toString() + "/";
    // 식단 리스트 불러오기!~
    @GetMapping(value = "/foodlist")
    public List<FoodVO> getFoodList() {
        return service.selectFoodList();
    }

    // 식단 상세 정보
    @GetMapping(value = "/detail")
    public FoodVO getFoodDetail(@RequestParam("nutrient_id") int nutrient_id) {
        FoodVO vo = service.FoodDetail(nutrient_id);
        return vo;
    }

    @GetMapping(value = "/foodmenu")
    public FoodVO getRandomFood() {
    List<FoodVO> foodList = service.selectFoodList();
    int randomIndex = new Random().nextInt(foodList.size());
    return foodList.get(randomIndex);
}
}
