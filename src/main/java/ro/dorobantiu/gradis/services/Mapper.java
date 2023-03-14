package ro.dorobantiu.gradis.services;

import org.springframework.stereotype.Component;
import ro.dorobantiu.gradis.DTOs.AuthorDTO;
import ro.dorobantiu.gradis.DTOs.DepartmentDTO;
import ro.dorobantiu.gradis.DTOs.FacultyDTO;
import ro.dorobantiu.gradis.DTOs.UserDTO;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.Department;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.entities.User;


@Component
public class Mapper {
    public FacultyDTO toDTO(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName());
    }

    public DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName(), toDTO(department.getFaculty()));
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getEmail(), user.getPassword(), user.isEnabled());
    }

    public AuthorDTO toDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getEmail(), toDTO(author.getDepartment()));
    }
}
