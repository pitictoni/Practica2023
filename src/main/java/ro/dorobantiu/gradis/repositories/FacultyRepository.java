package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, String> {
}
