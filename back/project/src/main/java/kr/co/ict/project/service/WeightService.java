package kr.co.ict.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.WeightDao;
import kr.co.ict.project.vo.WeightVO;

@Service
public class WeightService {
    @Autowired
    private WeightDao dao;

    public List<WeightVO> find() {
        return dao.find();
    }

    // public String insertWeight(WeightVO vo) {
    //     return dao.addWeight(vo);
    // }

    public void updateWeight(WeightVO vo){
        dao.upWeight(vo);
    }
}