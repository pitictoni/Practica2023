package ro.dorobantiu.gradis.DTOs;

import jakarta.persistence.*;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.Faculty;

import java.util.ArrayList;
import java.util.Collection;

public class DepartmentDTO {

    private String id;
    private String name;
    private FacultyDTO facultyDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacultyDTO getFacultyDTO() {
        return facultyDTO;
    }

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
    }

    public DepartmentDTO(String id, String name, FacultyDTO facultyDTO) {
        this.id = id;
        this.name = name;
        this.facultyDTO = facultyDTO;
    }

    public DepartmentDTO() {
    }
}
