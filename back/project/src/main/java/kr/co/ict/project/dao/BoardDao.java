package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.BoardVO;
import kr.co.ict.project.vo.BoardnextVO;
import kr.co.ict.project.vo.BoardprevVO;

@Mapper
public interface BoardDao {
    public List<BoardVO> selectList();
    public BoardVO selectDetail(int ono);
    public BoardprevVO prevDetail(int ono);
    public BoardnextVO nextDetail(int ono);
}
