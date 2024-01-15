package kr.co.ict.project.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.vo.MemberVO;

@Mapper
public interface MemberDao {

    public int addMember(MemberVO vo);
}
