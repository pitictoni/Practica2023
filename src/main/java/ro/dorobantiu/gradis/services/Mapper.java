package ro.dorobantiu.gradis.services;

import org.springframework.stereotype.Component;
import ro.dorobantiu.gradis.DTOs.*;
import ro.dorobantiu.gradis.entities.*;


@Component
public class Mapper {
    public FacultyDTO toDTO(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName());
    }
    public JournalDTO toDTO(Journal journal) {
        return new JournalDTO(journal.getId(), journal.getTitle(),journal.getImpactFactor(), journal.getIndexing(), journal.getWoSCathegory(), journal.getQuartil(),
                journal.getISSN(), journal.geteISSN());
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
