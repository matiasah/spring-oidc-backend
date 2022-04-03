package oidc.management.jwk.source.strategy;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.RSAKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Simple JWK source that generates a single RSA key pair at startup.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class SimpleJWKSource implements JWKSource<SecurityContext> {

    /**
     * RSA Key Generator used to generate the JWK.
     */
    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    /**
     * Current JWK Set.
     */
    private JWKSet jwkSet;

    @PostConstruct
    public void init() {
        // Generate RSA key
        JWK jwk = rsaKeyGenerator.generateRsa();

        // Create JWK set
        jwkSet = new JWKSet(jwk);
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
        return jwkSelector.select(jwkSet);
    }

}
