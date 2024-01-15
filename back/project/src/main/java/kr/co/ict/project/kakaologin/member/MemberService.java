package kr.co.ict.project.kakaologin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 로그인 및 내정보확인
    public MemberDto getMember(String id) {
        // id 조회 후, id 없으면 null반환
        Members entity = memberRepository.findById(id).orElse(null);
        // 객체 null 지정
        MemberDto dto = null;
        if (entity == null) {
            return dto;
        } else {
            dto = new MemberDto(entity.getId(), entity.getName(), null, entity.getBirth(), entity.getAddress(),
                    entity.getQuestion(), entity.getAnswer(), entity.getPhoneno(), entity.getEmail(),
                    entity.getGender(), entity.getHeight(), entity.getWeight());
            return dto;
        }
    }

    // 회 . 탈 .
    public void delMember(String id) {
        memberRepository.deleteById(id);
    }
}
