package oidc.management.jwk.source.strategy;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.RSAKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * JWKS source that returns a list of JWK for a single authorization server replica.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class ChangingJWKSource implements JWKSource<SecurityContext> {

    /**
     * RSA Key Generator used to generate the JWK.
     */
    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    /**
     * List of current JWKs.
     */
    private List<JWK> keys = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Generate initial RSA key
        RSAKey rsaKey = rsaKeyGenerator.generateRsa();

        // Add to list
        keys.add(rsaKey);
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
        return jwkSelector.select(getJWKSet());
    }

    public JWKSet getJWKSet() {
        return new JWKSet(this.keys);
    }

    /**
     * Gets the current JWK list.
     *
     * @return The current JWK list.
     */
    public List<JWK> getKeys() {
        return keys;
    }

    /**
     * Sets the current JWK list.
     *
     * @param keys The current JWK list.
     */
    public void setKeys(List<JWK> keys) {
        this.keys = keys;
    }

}
