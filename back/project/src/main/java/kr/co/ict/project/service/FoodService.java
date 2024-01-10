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

    public List<FoodVO> find() {
        return dao.find();
    }

    public int insertFood(FoodVO vo) {
        return dao.addFood(vo);
    }
}