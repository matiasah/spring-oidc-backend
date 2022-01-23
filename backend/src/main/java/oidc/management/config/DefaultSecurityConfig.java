package oidc.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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

    /**
     * Default Security Filter Chain.
     * 
     * @param http Security configuration.
     * @return Security Filter Chain.
     * @throws Exception
     */
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

    /**
     * Argon2 password encoder bean.
     * 
     * @return Argon2 password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

}
