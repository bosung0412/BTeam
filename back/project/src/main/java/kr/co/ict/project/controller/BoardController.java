package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.BoardService;
import kr.co.ict.project.vo.BoardVO;

@RestController
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/boardList")
    public List<BoardVO> listboard(){
        System.out.println("-------------boardController-------------------");
        return service.selectBoardList();
    }
    @PostMapping("/boardAdd")
    public int addBoard(@RequestBody BoardVO vo){
        int res = service.insertBoard(vo);
        return res;
    }
}