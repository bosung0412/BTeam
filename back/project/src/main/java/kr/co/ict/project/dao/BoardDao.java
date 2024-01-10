package kr.co.ict.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.BoardVO;

@Mapper
public interface BoardDao {
    public List<BoardVO> selectList();
    public int addBoard(BoardVO vo);
}
