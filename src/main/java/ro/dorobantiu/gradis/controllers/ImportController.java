package ro.dorobantiu.gradis.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.dorobantiu.gradis.DTOs.DepartmentDTO;
import ro.dorobantiu.gradis.DTOs.FacultyDTO;
import ro.dorobantiu.gradis.services.DepartmentServices;
import ro.dorobantiu.gradis.services.FacultyServices;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/imports")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImportController {
    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);
    @Autowired
    FacultyServices facultyServices;
    @Autowired
    DepartmentServices departmentServices;

    @PostMapping(value = "/importUsers", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void importUsers(@RequestPart MultipartFile file) throws IOException {
       // String tempExcelFile = excelUtil.saveToTempExcel(file);
        //excelUtil.importAuthors(tempExcelFile);
    }

    @PostMapping(value = "/importFaculties", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<FacultyDTO> importFaculties(@RequestPart MultipartFile file) throws IOException {
        return facultyServices.importFaculties(file.getInputStream());
    }

    @PostMapping(value = "/importDepartments", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<DepartmentDTO> importDepartments(@RequestPart MultipartFile file) throws IOException {
        return departmentServices.importDepartments(file.getInputStream());
    }

//    @PostMapping(value = "/users/{fileName}")
//    public String importUsers(@PathVariable String fileName){
//        log.info("" + fileName);
//        return fileName;
//    }
}
