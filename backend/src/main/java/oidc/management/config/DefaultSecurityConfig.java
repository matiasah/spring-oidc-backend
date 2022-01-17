package oidc.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL;
import static org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter.ERROR_PARAMETER_NAME;

/**
 * Security configuration.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see SecurityFilterChain
 * @see HttpSecurity
 * @see EnableWebSecurity
 */
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest()
                    .permitAll()
                .and()
            .formLogin()
                .permitAll()
                .loginPage(DEFAULT_LOGIN_PAGE_URL)
                .failureUrl(DEFAULT_LOGIN_PAGE_URL + "?" + ERROR_PARAMETER_NAME + "=true")
                .and()
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
