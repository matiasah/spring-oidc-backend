package oidc.management.jwk.strategy;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.JwkProvider;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * JWK Source strategy that caches the JWKs returned by the wrapped JWK provider.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class CachingJWKSourceStrategy implements JWKSource<SecurityContext> {

    /**
     * The JWK provider.
     */
    private JwkProvider jwkProvider;

    /**
     * Constructor for the class, receives a JwkProvider.
     *
     * @param jwkProvider
     */
    public CachingJWKSourceStrategy(JwkProvider jwkProvider) {
        // Get the JWK provider
        this.jwkProvider = jwkProvider;
    }

    @Override
    public List<JWK> get(final JWKSelector jwkSelector, final SecurityContext context) {
        // Return list of JWKs
        return this.getJwks();
    }

    @Cacheable(
            value = "${oidc.management.jwk.cache.value}",
            cacheManager = "${oidc.management.jwk.cache.cacheManager}",
            condition = "${oidc.management.jwk.cache.condition}"
    )
    public List<JWK> getJwks() {
        // Return list of JWKs
        return this.jwkProvider.getJwks();
    }

}
