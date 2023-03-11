package ro.dorobantiu.gradis.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.dorobantiu.gradis.repositories.*;
import ro.dorobantiu.gradis.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import ro.dorobantiu.gradis.helpers.ExcelUtil;


@Configuration
public class InitialDatabaseConfiguration {
    private static final Logger log = LoggerFactory.getLogger(InitialDatabaseConfiguration.class);

    private final PasswordEncoder passwordEncoder;

    public InitialDatabaseConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(TestRepository repository,
                                   UserRepository userRepository,
                                   AuthoritiesRepository authoritiesRepository,
                                   AuthorRepository authorRepository,
                                   DepartmentRepository departmentRepository,
                                   FacultyRepository facultyRepository,
                                   ExcelUtil excelUtil
    ) {
        return args -> {
//            Faculty faculty = new Faculty("Inginerie");
//            Department department = new Department("Calculatoare și Inginerie Electrică", faculty);
//            faculty.getDepartments().add(department);
//            Author author1 = new Author("Toni", department);
//            Author author2 = new Author("Alex", department);
//            department.getAuthors().add(author1);
//            department.getAuthors().add(author2);
//            log.info("Add test entity " + repository.save(new TestEntity("test", "test descr")));
//            log.info("Add test entity " + repository.save(new TestEntity("test1", "descr")));
//            log.info("Add user " + userRepository.save(new User("admin", passwordEncoder.encode("a"), true)));
//            log.info("Add user " + authoritiesRepository.save(new Authority("admin", "ROLE_READ_PRINCIPAL")));
//            log.info("Add faculty " + facultyRepository.save(faculty));
//            log.info("Add department " + departmentRepository.save(department));
//            log.info("Add author " + authorRepository.save(author1));
//            log.info("Add author " + authorRepository.save(author2));
//            log.info("Faculties " + excelUtil.getFaculties(new FileInputStream("documents/cadre didactice.xlsx")).toString());
        };
    }
}
