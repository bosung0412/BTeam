package kr.co.ict.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.dao.AppDao;
import kr.co.ict.project.dto.AndUploadDTO;

@Service
public class AppService {
    @Autowired
    private AppDao dao;

    public int addappImg(AndUploadDTO dto){
        return dao.addappimg(dto);
    }
}
