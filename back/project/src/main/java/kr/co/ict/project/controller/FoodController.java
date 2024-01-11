package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.FoodService;
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
    public int addFood(@ModelAttribute FoodVO vo) {
        int res = service.insertFood(vo);
        return res;
    }
}
