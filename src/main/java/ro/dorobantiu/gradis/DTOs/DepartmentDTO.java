package ro.dorobantiu.gradis.DTOs;

import jakarta.persistence.*;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.Faculty;

import java.util.ArrayList;
import java.util.Collection;

public record DepartmentDTO(String id, String name, FacultyDTO facultyDTO) {
}
