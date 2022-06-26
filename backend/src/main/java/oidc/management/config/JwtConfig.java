package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.strategy.FirstJWKSourceStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
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

    /**
     * Default JwtEncoder
     *
     * @return A instance of NimbusJwsEncoder that uses the first JWK found in the JWK Set.
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        // Create a JWK source that uses the first JWK found in the JWK Set.
        JWKSource<SecurityContext> firstJwkSource = new FirstJWKSourceStrategy(jwkSource);

        // Create a JWT encoder that uses the first JWK found in the JWK Set.
        return new NimbusJwtEncoder(firstJwkSource);
    }

}
