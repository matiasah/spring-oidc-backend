package oidc.management.jwk.strategy;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import java.util.Arrays;
import java.util.List;

/**
 * JWK source that returns the first JWK from any JWK source.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class FirstJWKSourceStrategy implements JWKSource<SecurityContext> {

    private JWKSource<SecurityContext> jwkSource;

    public FirstJWKSourceStrategy(JWKSource<SecurityContext> jwkSource) {
        this.jwkSource = jwkSource;
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
        // Get JWKs
        List<JWK> jwks = jwkSource.get(jwkSelector, context);

        // If there are no JWKs
        if (jwks.isEmpty()) {

            // Return the empty list
            return jwks;
        }

        // Get the first JWK
        JWK jwk = jwks.get(0);

        // Return a list with the first JWK
        return Arrays.asList(jwk);
    }

}
