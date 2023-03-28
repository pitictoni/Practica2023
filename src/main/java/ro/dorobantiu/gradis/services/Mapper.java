package ro.dorobantiu.gradis.services;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import ro.dorobantiu.gradis.DTOs.*;
import ro.dorobantiu.gradis.entities.*;


@Component
public class Mapper {
    public FacultyDTO toDTO(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName());
    }

    public JournalIdAndTitleDTO toDTO(Journal journal) {
        return new JournalIdAndTitleDTO(journal.getId(), journal.getTitle());
    }

    public DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName(), toDTO(department.getFaculty()));
    }

    public PaperDTO toDTO(Paper paper) {
        return new PaperDTO(paper.getId(), paper.getTitle(), paper.getRawAuthorList());
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getEmail(), user.getPassword(), user.isEnabled());
    }

    public AuthorDTO toDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getEmail(), toDTO(author.getDepartment()));
    }

    public AuthorEmailDTO toEmailDTO(Author a) {
        return new AuthorEmailDTO(a.getEmail());
    }
}
