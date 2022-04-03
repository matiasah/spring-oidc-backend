package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import oidc.management.jwk.JwkProvider;
import oidc.management.jwk.RSAKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * JWK provider that changes the JWKs it provides over time.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class InMemoryChangingJwkProvider implements JwkProvider {

    /**
     * RSA Key Generator used to generate the JWK.
     */
    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    /**
     * List of current JWKs.
     */
    private List<JWK> jwks = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Generate initial RSA key
        RSAKey rsaKey = rsaKeyGenerator.generateRsa();

        // Add to list
        jwks.add(rsaKey);
    }

    @Override
    public List<JWK> getJwks() {
        return jwks;
    }

}
