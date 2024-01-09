package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.WeightService;
import kr.co.ict.project.vo.WeightVO;

@RestController // Json형태로 객체 데이터 반환(controller + responseBody)
public class WeightController {
    @Autowired
    private WeightService service;

    @GetMapping("/weightlist")
    public List<WeightVO> listweight() {
        return service.find();
    }
}
