package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
/*
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableAsync
@EnableWebSecurity
*/
public class SecurityConfig {
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeRequests()
                //permis all swagger URL'S
                .requestMatchers(
                        "/h2-console/**",  // Autoriser l'accès à la console H2
                        "/swagger-ui.html/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**"
                ).permitAll();
                /*
                .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults());


        return http.build();
    }
    */
}