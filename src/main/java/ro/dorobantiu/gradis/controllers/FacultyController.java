package ro.dorobantiu.gradis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dorobantiu.gradis.DTOs.AuthorDTO;
import ro.dorobantiu.gradis.DTOs.FacultyDTO;
import ro.dorobantiu.gradis.services.AuthorServices;
import ro.dorobantiu.gradis.services.FacultyServices;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FacultyController {

    @Autowired
    FacultyServices facultyServices;
    @GetMapping(value = "/all")
    public Collection<FacultyDTO> getAllFaculties() {
        return facultyServices.getAllFaculties();
    }
}
