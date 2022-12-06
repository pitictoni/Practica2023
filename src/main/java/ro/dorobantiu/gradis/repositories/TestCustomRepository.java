package ro.dorobantiu.gradis.repositories;

import org.springframework.stereotype.Repository;
import ro.dorobantiu.gradis.entities.TestEntity;

import java.util.List;

@Repository
public interface TestCustomRepository {
    List<TestEntity> findTestEntitiesByListOfNames(List<String> names);
}
