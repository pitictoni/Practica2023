package ro.dorobantiu.gradis.DTOs;

import jakarta.persistence.*;
import ro.dorobantiu.gradis.entities.Department;
import ro.dorobantiu.gradis.entities.Paper;

import java.util.ArrayList;
import java.util.Collection;

public class AuthorDTO {
    private String id;
    private String name;
    private String email;
    private DepartmentDTO departmentDTO;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public AuthorDTO() {
    }

    public AuthorDTO(String id, String name, String email, DepartmentDTO departmentDTO) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentDTO = departmentDTO;
    }
}
