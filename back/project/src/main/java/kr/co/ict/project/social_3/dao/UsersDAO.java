package kr.co.ict.project.social_3.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.co.ict.project.social_3.DTO.UsersDTO;
import kr.co.ict.project.social_3.mapper.UsersMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersDAO {

    private final UsersMapper usersMapper;

    public int createUser(UsersDTO usersDTO) {
        return usersMapper.createUser(usersDTO);
    }// createUser

    public int createKakaoUser(UsersDTO usersDTO) {
        return usersMapper.createKakaoUser(usersDTO);
    }// createKakaoUser

    public Optional<UsersDTO> login(UsersDTO usersDTO) {
        return usersMapper.login(usersDTO);
    }// login

    public Optional<UsersDTO> getUserInfo(UsersDTO usersDTO) {
        return usersMapper.getUserInfoByUnum(usersDTO);
    }// getUserInfo

    public Optional<UsersDTO> getUserInfoByKakaoId(UsersDTO usersDTO) {
        return usersMapper.getUserInfoByKakaoId(usersDTO);
    }// getUserInfoByKakaoId

}// UsersDAO
