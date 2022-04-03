package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.SingleJWKSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwkConfig {

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.strategy", havingValue = "single")
    public JWKSource<SecurityContext> singleJwkSource() {
        return new SingleJWKSource();
    }

}
