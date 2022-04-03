package oidc.management.jwk;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * JWKS source that returns a list of JWK for a single authorization server replica.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class SingleJWKSource implements JWKSource<SecurityContext> {

    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    private RSAKey rsaKey;
    private JWKSet jwkSet;

    @PostConstruct
    public void init() {
        rsaKey = rsaKeyGenerator.generateRsa();
        jwkSet = new JWKSet(rsaKey);
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
        return jwkSelector.select(jwkSet);
    }

}
