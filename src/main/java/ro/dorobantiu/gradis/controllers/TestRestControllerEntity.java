package ro.dorobantiu.gradis.controllers;

import jakarta.ws.rs.Produces;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.dorobantiu.gradis.entities.TestEntity;
import ro.dorobantiu.gradis.repositories.TestCustomRepository;
import ro.dorobantiu.gradis.repositories.TestRepository;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestRestControllerEntity {

    final TestRepository testRepository;

    final TestCustomRepository testCustomRepository;

    public TestRestControllerEntity(TestRepository testRepository, TestCustomRepository testCustomRepository) {
        this.testRepository = testRepository;
        this.testCustomRepository = testCustomRepository;
    }

    @GetMapping("/testEntityByName")
    @Produces("application/json")
    public TestEntity testEntityByName(@RequestParam(value = "name") String name) {
        return testRepository.findByName(name);
    }

    @GetMapping("/testEntityByNameOrDescription")
    @Produces("application/json")
    public List<TestEntity> testEntityByNameOrDescription(@RequestParam(value = "value") String value) {
        return testRepository.findByNameOrDescription(value, Pageable.ofSize(1));
    }

    @GetMapping("/updateDescriptionForEntityWithName")
    @Produces("application/json")
    public int updateDescriptionForEntityWithName(@RequestParam(value = "name") String name,
                                                  @RequestParam(value = "description") String description) {
        return testRepository.updateDescriptionForEntityWithName(name, description);
    }

    @GetMapping("/testEntityByNames")
    @Produces("application/json")
    public List<TestEntity> testEntityByNames(@RequestParam(value = "names") String names) {
        if (names == null || names.isBlank()) {
            return null;
        }
        return testCustomRepository.findTestEntitiesByListOfNames(Arrays.asList(names.split(", ")));
    }
}
