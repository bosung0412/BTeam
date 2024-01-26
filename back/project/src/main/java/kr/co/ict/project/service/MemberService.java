package kr.co.ict.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.MemberDao;
import kr.co.ict.project.vo.MemberVO;

@Service
public class MemberService {
    @Autowired
    private MemberDao dao;

    public int addMember(MemberVO vo) {
        return dao.addMember(vo);
    }
    public MemberVO getMemHeight(int member_no) {
        return dao.getMemHeight(member_no);
    }
}
