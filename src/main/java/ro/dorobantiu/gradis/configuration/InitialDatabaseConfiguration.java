package ro.dorobantiu.gradis.configuration;

import org.springframework.context.annotation.Configuration;
import ro.dorobantiu.gradis.entities.TestEntity;
import ro.dorobantiu.gradis.repositories.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Configuration
public class InitialDatabaseConfiguration {
    private static final Logger log = LoggerFactory.getLogger(InitialDatabaseConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(TestRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new TestEntity("test", "test descr")));
            log.info("Preloading " + repository.save(new TestEntity("test1", "descr")));
        };
    }
}
