package ro.dorobantiu.gradis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.dorobantiu.gradis.DTOs.AuthorDTO;
import ro.dorobantiu.gradis.services.AuthorServices;

import java.util.Collection;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthorController {

    @Autowired
    AuthorServices authorServices;

    @GetMapping(value = "/all")
    public Collection<AuthorDTO> getAuthors() {
        return authorServices.getAllAuthors();
    }

    @GetMapping(value = "name")
    public Collection<AuthorDTO> getAuthorsByName(@RequestParam(value = "name") String name){
        return authorServices.getAuthorsByName(name);
    }
}

