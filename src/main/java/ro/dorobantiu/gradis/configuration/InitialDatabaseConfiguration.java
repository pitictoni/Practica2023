package ro.dorobantiu.gradis.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.dorobantiu.gradis.entities.Authority;
import ro.dorobantiu.gradis.entities.TestEntity;
import ro.dorobantiu.gradis.entities.User;
import ro.dorobantiu.gradis.repositories.AuthoritiesRepository;
import ro.dorobantiu.gradis.repositories.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import ro.dorobantiu.gradis.repositories.UserRepository;


@Configuration
public class InitialDatabaseConfiguration {
    private static final Logger log = LoggerFactory.getLogger(InitialDatabaseConfiguration.class);

    private final PasswordEncoder passwordEncoder;

    public InitialDatabaseConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(TestRepository repository, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        return args -> {
            log.info("Add test entity " + repository.save(new TestEntity("test", "test descr")));
            log.info("Add test entity " + repository.save(new TestEntity("test1", "descr")));
            log.info("Add user " + userRepository.save(new User("admin", passwordEncoder.encode("a"), true)));
            log.info("Add user " + authoritiesRepository.save(new Authority("admin", "ROLE_READ_PRINCIPAL")));
        };
    }
}
