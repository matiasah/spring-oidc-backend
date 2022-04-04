package oidc.management.jwk.strategy;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.JwkProvider;

import java.util.List;

/**
 * Simple JWK source that generates a single RSA key pair at startup.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class SimpleJWKSourceStrategy implements JWKSource<SecurityContext> {

    /**
     * The JWK provider.
     */
    private JwkProvider jwkProvider;

    /**
     * Constructor for the class, receives a JwkProvider.
     *
     * @param jwkProvider
     */
    public SimpleJWKSourceStrategy(JwkProvider jwkProvider) {
        // Get the JWK provider
        this.jwkProvider = jwkProvider;
    }

    @Override
    public List<JWK> get(final JWKSelector jwkSelector, final SecurityContext context) {
        // Return list of JWKs
        return this.jwkProvider.getJwks();
    }

}
