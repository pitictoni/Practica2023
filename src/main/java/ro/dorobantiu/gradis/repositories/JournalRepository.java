package ro.dorobantiu.gradis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.dorobantiu.gradis.entities.Journal;

@Repository
public interface JournalRepository extends CrudRepository<Journal, String> {
}
