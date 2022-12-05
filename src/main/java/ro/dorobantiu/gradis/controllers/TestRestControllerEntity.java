package ro.dorobantiu.gradis.controllers;

import jakarta.ws.rs.Produces;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.dorobantiu.gradis.entities.TestEntity;
import ro.dorobantiu.gradis.repositories.TestRepository;

@RestController
public class TestRestControllerEntity {

    final TestRepository testRepository;

    public TestRestControllerEntity(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping("/testEntityByName")
    @Produces("application/json")
    public TestEntity testEntityByName(@RequestParam(value = "name") String name) {
        return testRepository.findByName(name);
    }
}
