package kr.co.ict.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.BoardDao;
import kr.co.ict.project.vo.BoardVO;
import kr.co.ict.project.vo.BoardnextVO;
import kr.co.ict.project.vo.BoardprevVO;

@Service
public class BoardService {
    @Autowired
    private BoardDao dao;

    public List<BoardVO> selectBoardList(){
        return dao.selectList();
    }
    public BoardVO selectDetail(int ono){
        return dao.selectDetail(ono);
    }
    public BoardprevVO prevDetail(int ono){
        return dao.prevDetail(ono);
    }
    public BoardnextVO nextDetail(int ono){
        return dao.nextDetail(ono);
    }
}
