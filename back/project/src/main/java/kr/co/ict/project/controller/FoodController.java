package kr.co.ict.project.controller;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ict.project.service.FoodService;
import kr.co.ict.project.vo.FoodVO;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FoodController {

    @Autowired
    private FoodService service;

    // 정적 리소스를 배포할 수 있도록 경로 지정
    private final static String imageDirectory = Paths.get("").toAbsolutePath()
            .resolve("src/main/resources/static/images").toString() + "/";

    // 식단 리스트 불러오기!~
    @GetMapping(value = "/foodlist")
    public List<FoodVO> getFoodList() {
        return service.selectFoodList();
    }

    // 식단 상세 정보
    @GetMapping(value = "/detail")
    public FoodVO getFoodDetail(@RequestParam("fno") int fno) {
        FoodVO vo = service.FoodDetail(fno);
        return vo;
    }
    

    @PostMapping("/foodadd")
    public int addFood(@RequestParam List<MultipartFile> files, @ModelAttribute FoodVO vo) {
         // 사진 이름들을 합칠 문자열 생성
        StringBuilder filenames = new StringBuilder();
        // 파일 저장하기
        for (MultipartFile multipartFile : files) {
            String filename = UUID.randomUUID() + getExtension(multipartFile);
            filenames.append(",").append(filename);
            String filePath = imageDirectory + filename;

            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());
            } catch (Exception e) {
                // log.error(e.getMessage(), e);
                throw new RuntimeException("Fail to upload files.");
            }
        }
        int res = service.insertFood(vo);
        return res;
    }
    // 은닉화. 내부에서 동작하는 메서드. 노출시키지 않음
    private String getExtension(MultipartFile multipartFile) {
        // 업로드된 파일의 이름을 변수에 저장
        String fileName = multipartFile.getOriginalFilename();

        int index = fileName.indexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    }
}
