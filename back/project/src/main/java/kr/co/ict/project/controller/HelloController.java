package kr.co.ict.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "안녕하세요!!";

    }
}
