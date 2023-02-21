package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {
}
