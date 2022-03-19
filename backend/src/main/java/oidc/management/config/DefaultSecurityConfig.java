package oidc.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
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
 * @see CorsConfigurationSource
 * @see JwtAuthenticationConverter
 */
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    private JwtAuthenticationConverter jwtAuthenticationConverter;

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
            .cors()
                .configurationSource(corsConfigurationSource)
                .and()
            .authorizeRequests()
                .anyRequest()
                    .permitAll()
                .and()
            .formLogin()
                .permitAll()
                .loginPage(DEFAULT_LOGIN_PAGE_URL)
                .failureUrl(DEFAULT_LOGIN_PAGE_URL + "?" + ERROR_PARAMETER_NAME + "=true")
                .and()
            .oauth2ResourceServer()
                .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter)
                    .and()
                .and()
            .build();
    }

}
