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

    // public List<WeightVO> find() {
    // return dao.find();
    // }

    public WeightVO weightFirst(int member_no) {
        return dao.weightuser(member_no);
    }

    public WeightVO weightFuture(int member_no) {
        return dao.weightfuture(member_no);
    }

    public int insertWeight(WeightVO vo) {
        return dao.addweight(vo);
    }

    public int updateWeight(WeightVO vo) {
        return dao.upweight(vo);
    }

    public WeightVO lastweight(int member_no) {
        return dao.weightlast(member_no);
    }

    public List<WeightVO> getWeightLogs(int member_no) {
        return dao.getWeightLogs(member_no);
    }
}