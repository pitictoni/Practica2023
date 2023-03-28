package ro.dorobantiu.gradis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.dorobantiu.gradis.DTOs.*;
import ro.dorobantiu.gradis.services.*;

import java.io.IOException;
import java.util.Collection;


@RestController
@RequestMapping("/imports")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImportController {
    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);
    @Autowired
    FacultyServices facultyServices;
    @Autowired
    UserServices userServices;
    @Autowired
    DepartmentServices departmentServices;
    @Autowired
    AuthorServices authorServices;
    @Autowired
    JournalServices journalServices;
    @Autowired
    PaperServices paperServices;

    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Collection<UserDTO> importUsers(@RequestPart MultipartFile file) throws IOException {
        return userServices.importUsers(file.getInputStream());
    }

    @PostMapping(value = "/journals", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Collection<JournalIdAndTitleDTO> importJournals(@RequestPart MultipartFile file) throws IOException {
        return journalServices.importJournals(file.getInputStream());
    }

    @PostMapping(value = "/paperByURL")
    public PaperDTO articleByURL(@RequestParam String url) {
        return paperServices.importPaperFromURL(url);
    }

    @PostMapping(value = "/papers", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Collection<PaperDTO> importPapers(@RequestPart MultipartFile file) throws IOException {
        return paperServices.importPapers(file.getInputStream());
    }

    @PostMapping(value = "/authors", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Collection<AuthorDTO> importAuthors(@RequestPart MultipartFile file) throws IOException {
        return authorServices.importAuthors(file.getInputStream());
    }

    @PostMapping(value = "/faculties", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Collection<FacultyDTO> importFaculties(@RequestPart MultipartFile file) throws IOException {
        return facultyServices.importFaculties(file.getInputStream());
    }

    @PostMapping(value = "/departments", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Collection<DepartmentDTO> importDepartments(@RequestPart MultipartFile file) throws IOException {
        return departmentServices.importDepartments(file.getInputStream());
    }

    @PostMapping(value = "/all", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String importAll(@RequestPart MultipartFile file) throws IOException {
        facultyServices.importFaculties(file.getInputStream());
        departmentServices.importDepartments(file.getInputStream());
        userServices.importUsers(file.getInputStream());
        authorServices.importAuthors(file.getInputStream());
        return "Faculties, Departments, Users, Authors  imported";
    }

}
