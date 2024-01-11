package kr.co.ict.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.service.BoardService;
import kr.co.ict.project.vo.BoardVO;
import kr.co.ict.project.vo.BoardnextVO;
import kr.co.ict.project.vo.BoardprevVO;

@RestController
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/boardList")
    public List<BoardVO> listboard(){
        return service.selectBoardList();
    }
    @RequestMapping("/boardDetail")
    public BoardVO detailBoard(@RequestParam int ono){
        System.out.println("dedailBoard " + ono);
        System.out.println(service.selectDetail(ono).getOname());
        return service.selectDetail(ono);
    }
    @RequestMapping("/prevDetail")
    public BoardprevVO prevBoard(@RequestParam int ono){
        System.out.println("prevBoard " + ono);
        System.out.println(service.prevDetail(ono));
        return service.prevDetail(ono);
    }
    @RequestMapping("/nextDetail")
    public BoardnextVO nextBoard(@RequestParam int ono){
        System.out.println("nextDetail " +  service.nextDetail(ono));
        return service.nextDetail(ono);
    }
}