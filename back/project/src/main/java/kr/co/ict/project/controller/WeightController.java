package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.WeightService;
import kr.co.ict.project.vo.WeightVO;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // Json형태로 객체 데이터 반환(controller + responseBody)
@RequestMapping("/api/v1/auth")
public class WeightController {
    @Autowired
    private WeightService service;

    // @GetMapping("/weightlist")
    // public List<WeightVO> listweight() {
    // return service.find();
    // }

    @PostMapping("/upweight")
    public int insertWeight(@RequestBody WeightVO vo) {
        int res = service.insertWeight(vo);
        System.out.println(res);
        return res;
    }
    @GetMapping("/gethistweight")
    public WeightVO getHistWeight(@RequestParam int member_no) {
        return service.getHistWeight(member_no);
    }

    @GetMapping("/weight")
    public WeightVO firstweight(int member_no) {
        return service.weightFirst(member_no);
    }

    @GetMapping("/fweight")
    public WeightVO fweight(int member_no) {
        return service.weightFuture(member_no);
    }

    @PostMapping("/updateweight")
    public int updateweight(@RequestBody WeightVO vo) {
        vo.setWeight(vo.getCurrentweight());
        int res = service.updateWeight(vo);
        return res;
    }

    @GetMapping("/lastWeightEntry")
    public WeightVO weightlast(int member_no) {
        return service.lastweight(member_no);
    }

    @GetMapping("/logweight")
    public List<WeightVO> getWeightLogs(int member_no) {
        return service.getWeightLogs(member_no);
    }
}
