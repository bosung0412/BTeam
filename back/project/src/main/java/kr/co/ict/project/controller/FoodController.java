package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.FoodService;
import kr.co.ict.project.vo.FoodVO;

@RestController
public class FoodController {
    @Autowired
    private FoodService service;

    @GetMapping("/foodlist")
    public List<FoodVO> listweight() {
        return service.find();
    }

    @GetMapping("/foodAdd")
    public int addFood(@RequestBody FoodVO vo) {
        int res = service.insertFood(vo);
        return res;
    }
}
