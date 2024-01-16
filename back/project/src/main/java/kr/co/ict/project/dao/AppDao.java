package kr.co.ict.project.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ict.project.dto.AndUploadDTO;

@Mapper
public interface AppDao {

    public int addappimg(AndUploadDTO dto);
}
