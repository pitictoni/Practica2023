package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.dorobantiu.gradis.entities.Faculty;

@Repository
public interface FacultyRepository extends PagingAndSortingRepository<Faculty, String>,
        CrudRepository<Faculty, String> {

    Faculty findByName(String facultyName);

}
