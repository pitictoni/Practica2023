package ro.dorobantiu.gradis.services;

import org.springframework.stereotype.Component;
import ro.dorobantiu.gradis.DTOs.DepartmentDTO;
import ro.dorobantiu.gradis.DTOs.FacultyDTO;
import ro.dorobantiu.gradis.entities.Department;
import ro.dorobantiu.gradis.entities.Faculty;


@Component
public class Mapper {
    public FacultyDTO toDTO(Faculty faculty){
        return new FacultyDTO(faculty.getId(), faculty.getName());
    }

    public DepartmentDTO toDTO(Department department){
        return new DepartmentDTO(department.getId(), department.getName(),toDTO(department.getFaculty()));
    }

}
