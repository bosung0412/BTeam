package kr.co.ict.project.controller;

import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
            .resolve("back/project/src/main/resources/static/images").toString() + "/";
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
    

    @PostMapping(value = "/foodadd", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> handleFileUpload(@RequestParam("fname") String fname,
                                                @RequestParam("fprovince") String fprovince,
                                                @RequestParam("ffprovince") String ffprovince,
                                                @RequestParam("fcholesterol") String fcholesterol,
                                                @RequestParam("fsalt") String fsalt,
                                                @RequestParam("fpotasium") String fpotasium,
                                                @RequestParam("fcarbohydrate") String fcarbohydrate,
                                                @RequestParam("fdietaryfiber") String fdietaryfiber,
                                                @RequestParam("fprotein") String fprotein,
                                                @RequestParam("tocal") String tocal,
                                                @RequestParam("fimage_file_name") List<MultipartFile> files) {

        System.out.println("Code");
        String fimage_file_name = null;
        for (MultipartFile multipartFile : files) {
            String originalFileName = multipartFile.getOriginalFilename();
            String extension = getExtension(originalFileName);
            fimage_file_name = UUID.randomUUID().toString() + extension;
            String filePath = imageDirectory + fimage_file_name;
            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Fail to upload files.");
            }
        }

        // URL 디코딩 수행
        try {
            fname = URLDecoder.decode(fname, "UTF-8");
            fprovince = URLDecoder.decode(fprovince, "UTF-8");
            ffprovince = URLDecoder.decode(ffprovince, "UTF-8");
            fcholesterol = URLDecoder.decode(fcholesterol, "UTF-8");
            fsalt = URLDecoder.decode(fsalt, "UTF-8");
            fpotasium = URLDecoder.decode(fpotasium, "UTF-8");
            fcarbohydrate = URLDecoder.decode(fcarbohydrate, "UTF-8");
            fdietaryfiber = URLDecoder.decode(fdietaryfiber, "UTF-8");
            fprotein = URLDecoder.decode(fprotein, "UTF-8");
            tocal = URLDecoder.decode(tocal, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(fname);

    
        FoodVO vo = new FoodVO();
        vo.setFname(fname);
        vo.setFprovince(fprovince);
        vo.setFfprovince(ffprovince);
        vo.setFcholesterol(fcholesterol);
        vo.setFsalt(fsalt);
        vo.setFpotasium(fpotasium);
        vo.setFcarbohydrate(fcarbohydrate);
        vo.setFdietaryfiber(fdietaryfiber);
        vo.setFprotein(fprotein);
        vo.setTocal(tocal);
        vo.setFimage_file_name(fimage_file_name);

        service.insertFood(vo);

        System.out.println("ImgName: " + fimage_file_name);
        return ResponseEntity.ok().body(vo);
    }

    private String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    
}
}
