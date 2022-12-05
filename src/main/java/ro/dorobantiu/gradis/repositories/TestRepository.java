package ro.dorobantiu.gradis.repositories;


import org.springframework.data.repository.CrudRepository;
import ro.dorobantiu.gradis.entities.TestEntity;

public interface TestRepository extends CrudRepository<TestEntity, String> {

    TestEntity findByName(String name);

}