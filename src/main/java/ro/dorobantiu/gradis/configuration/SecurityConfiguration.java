package ro.dorobantiu.gradis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
//                .requestMatchers("/hello").permitAll()
//                .requestMatchers("/principal").hasRole("READ_PRINCIPAL")
//                .anyRequest().authenticated().and().formLogin();
        .anyRequest().permitAll();

        httpSecurity
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/hello")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies()
                );
        //httpSecurity.cors().and().csrf().ignoringRequestMatchers("/upload");
        httpSecurity.cors().and().csrf().disable();
        httpSecurity.headers().frameOptions().sameOrigin();
        return httpSecurity.build();
    }
}