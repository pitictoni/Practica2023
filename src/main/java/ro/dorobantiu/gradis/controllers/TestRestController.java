package ro.dorobantiu.gradis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.dorobantiu.gradis.services.helper.ExcelUtil;

import java.io.IOException;

@RestController
public class TestRestController {

    ExcelUtil excelUtil;
    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);

    public TestRestController(ExcelUtil excelUtil) {
        this.excelUtil = excelUtil;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadExcelData(@RequestPart MultipartFile file) throws IOException {
        log.info("" + file.getBytes().length);
        return excelUtil.test(file.getBytes());
    }
}