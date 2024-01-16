package kr.co.ict.project.controller;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
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
    public FoodVO getFoodDetail(@RequestParam("nutrient_id") int nutrient_id) {
        FoodVO vo = service.FoodDetail(nutrient_id);
        return vo;
    }
    

    @PostMapping(value = "/foodadd", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> handleFileUpload(@RequestParam("name") String name,
                                                @RequestParam("weight") int weight,
                                                @RequestParam("cal") int cal,
                                                @RequestParam("carbo") int carbo,
                                                @RequestParam("sugars") int sugars,
                                                @RequestParam("fat") int fat,
                                                @RequestParam("protein") int protein,
                                                @RequestParam("calcium") int calcium,
                                                @RequestParam("phosphorus") int phosphorus,
                                                @RequestParam("sodium") int sodium,
                                                @RequestParam("potassium") int potassium,
                                                @RequestParam("magnesium") int magnesium,
                                                @RequestParam("iron") int iron,
                                                @RequestParam("zinc") int zinc,
                                                @RequestParam("cholesterol") int cholesterol,
                                                @RequestParam("transfat") int transfat,
                                                @RequestParam("food_img") List<MultipartFile> files) {

        System.out.println("Code");
        String food_img = null;
        for (MultipartFile multipartFile : files) {
            String originalFileName = multipartFile.getOriginalFilename();
            String extension = getExtension(originalFileName);
            food_img = UUID.randomUUID().toString() + extension;
            String filePath = imageDirectory + food_img;
            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Fail to upload files.");
            }
        }

        // URL 디코딩 수행
            try {
        name = URLDecoder.decode(name, "UTF-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

    // 다른 변수들도 유사하게 처리
    try {
        weight = Integer.parseInt(URLDecoder.decode(String.valueOf(weight), "UTF-8"));
        cal = Integer.parseInt(URLDecoder.decode(String.valueOf(cal), "UTF-8"));
        carbo = Integer.parseInt(URLDecoder.decode(String.valueOf(carbo), "UTF-8"));
        sugars = Integer.parseInt(URLDecoder.decode(String.valueOf(sugars), "UTF-8"));
        fat = Integer.parseInt(URLDecoder.decode(String.valueOf(fat), "UTF-8"));
        protein = Integer.parseInt(URLDecoder.decode(String.valueOf(protein), "UTF-8"));
        calcium = Integer.parseInt(URLDecoder.decode(String.valueOf(calcium), "UTF-8"));
        phosphorus = Integer.parseInt(URLDecoder.decode(String.valueOf(phosphorus), "UTF-8"));
        sodium = Integer.parseInt(URLDecoder.decode(String.valueOf(sodium), "UTF-8"));
        potassium = Integer.parseInt(URLDecoder.decode(String.valueOf(potassium), "UTF-8"));
        magnesium = Integer.parseInt(URLDecoder.decode(String.valueOf(magnesium), "UTF-8"));
        iron = Integer.parseInt(URLDecoder.decode(String.valueOf(iron), "UTF-8"));
        zinc = Integer.parseInt(URLDecoder.decode(String.valueOf(zinc), "UTF-8"));
        cholesterol = Integer.parseInt(URLDecoder.decode(String.valueOf(cholesterol), "UTF-8"));
        transfat = Integer.parseInt(URLDecoder.decode(String.valueOf(transfat), "UTF-8"));
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }


        System.out.println(name);

    
        FoodVO vo = new FoodVO();
        vo.setName(name);
        vo.setWeight(weight);
        vo.setCal(cal);
        vo.setCarbo(carbo);
        vo.setSugars(sugars);
        vo.setFat(fat);
        vo.setProtein(protein);
        vo.setCalcium(calcium);
        vo.setPhosphorus(phosphorus);
        vo.setSodium(sodium);
        vo.setPotassium(potassium);
        vo.setMagnesium(magnesium);
        vo.setIron(iron);
        vo.setZinc(zinc);
        vo.setCholesterol(cholesterol);
        vo.setTransfat(transfat);
        vo.setFood_img(food_img);

        service.insertFood(vo);

        System.out.println("ImgName: " + food_img);
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
