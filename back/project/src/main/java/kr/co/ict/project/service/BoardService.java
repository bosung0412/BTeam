package kr.co.ict.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.BoardDao;
import kr.co.ict.project.vo.BoardVO;

@Service
public class BoardService {
    @Autowired
    private BoardDao dao;

    public List<BoardVO> selectBoardList(){
        return dao.selectList();
    }
    public int insertBoard(BoardVO vo){
        return dao.addBoard(vo);
    }
}
