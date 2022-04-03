package oidc.management.jwk;

import com.nimbusds.jose.jwk.JWK;

import java.util.List;

/**
 * Template for JWK provider.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public interface JwkProvider {

    /**
     * Pulls JWKs from the provider.
     *
     * @return List of JWKs.
     */
    public List<JWK> getJwks();

}
