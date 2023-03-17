package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.entities.Paper;

@Repository
public interface PaperRepository extends PagingAndSortingRepository<Paper, String>,
        CrudRepository<Paper, String> {

}
