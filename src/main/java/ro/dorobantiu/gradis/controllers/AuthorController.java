package ro.dorobantiu.gradis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.dorobantiu.gradis.DTOs.AuthorDTO;
import ro.dorobantiu.gradis.DTOs.AuthorEmailDTO;
import ro.dorobantiu.gradis.DTOs.FacultyDTO;
import ro.dorobantiu.gradis.services.AuthorServices;
import ro.dorobantiu.gradis.services.FacultyServices;

import java.util.Collection;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthorController {

    @Autowired
    AuthorServices authorServices;
    @Autowired
    FacultyServices facultyServices;

    @GetMapping(value = "/all")
    public Collection<AuthorDTO> getAuthors() {
        return authorServices.getAllAuthors();
    }

    @GetMapping(value = "name")
    public Collection<AuthorDTO> getAuthorsByName(@RequestParam(value = "name") String name){
        return authorServices.getAuthorsByName(name);
    }

    @GetMapping(value = "/emails")
    public Collection<AuthorEmailDTO> getAuthorsEmail() {
        return authorServices.getAuthorsMail();
    }

    @GetMapping(value = "/faculties")
    public Collection<FacultyDTO> getFaculties() {
        return facultyServices.getAllFaculties();
    }


}

