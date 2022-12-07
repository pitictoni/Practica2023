package ro.dorobantiu.gradis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class LoginSecurityConfiguration {

    private final DataSource dataSource;

    public LoginSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from gradis_users where email = ?")
                .authoritiesByUsernameQuery("select email, authority from gradis_authorities where email = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
