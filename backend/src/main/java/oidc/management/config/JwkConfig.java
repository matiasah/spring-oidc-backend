package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.source.strategy.SimpleJWKSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwkConfig {

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.strategy", havingValue = "simple")
    public JWKSource<SecurityContext> simpleJwkSource() {
        return new SimpleJWKSource();
    }

}
