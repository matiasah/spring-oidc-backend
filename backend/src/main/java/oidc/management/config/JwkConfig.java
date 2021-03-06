package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.JwkProvider;
import oidc.management.jwk.provider.MongoJwkProvider;
import oidc.management.jwk.provider.RenewingJwkProvider;
import oidc.management.jwk.provider.SimpleJwkProvider;
import oidc.management.jwk.provider.VaultJwkProvider;
import oidc.management.jwk.strategy.CachingJWKSourceStrategy;
import oidc.management.jwk.strategy.ScheduledJWKSourceStrategy;
import oidc.management.jwk.strategy.SimpleJWKSourceStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwkConfig {

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.strategy", havingValue = "simple")
    public JWKSource<SecurityContext> simpleJwkSourceStrategy(JwkProvider jwkProvider) {
        return new SimpleJWKSourceStrategy(jwkProvider);
    }

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.strategy", havingValue = "scheduled")
    public JWKSource<SecurityContext> scheduledJwkSourceStrategy(JwkProvider jwkProvider) {
        return new ScheduledJWKSourceStrategy(jwkProvider);
    }

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.strategy", havingValue = "caching")
    public JWKSource<SecurityContext> cachingJwkSourceStrategy(JwkProvider jwkProvider) {
        return new CachingJWKSourceStrategy(jwkProvider);
    }

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.provider", havingValue = "simple")
    public JwkProvider simpleJwkProvider() {
        return new SimpleJwkProvider();
    }

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.provider", havingValue = "renewing")
    public JwkProvider renewingJwkProvider() {
        return new RenewingJwkProvider();
    }

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.provider", havingValue = "vault")
    public JwkProvider vaultJwkProvider() {
        return new VaultJwkProvider();
    }

    @Bean
    @ConditionalOnProperty(name = "oidc.management.jwk.provider", havingValue = "mongodb")
    public JwkProvider mongoJwkProvider() {
        return new MongoJwkProvider();
    }

}
