package oidc.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * JWT configuration.
 *
 * @author Matias Hermosilla
 * @since 19-03-2022
 */
@Configuration
public class JwtConfig {

    /**
     * Default JWT Granted Authorities Converter bean.
     *
     * @return JWT Granted Authorities Converter.
     */
    @Bean
    public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        return new JwtGrantedAuthoritiesConverter();
    }

    /**
     * Default JWT Authentication Converter bean.
     *
     * @return JWT Authentication Converter.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

}
