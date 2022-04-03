package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import oidc.management.jwk.JwkProvider;
import oidc.management.jwk.RSAKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * A simple JWK provider that returns a fixed JWK that is generated on startup.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class SimpleJwkProvider implements JwkProvider {

    /**
     * RSA Key Generator used to generate the JWK.
     */
    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    /**
     * The current JWK.
     */
    private RSAKey rsaKey;

    @PostConstruct
    public void init() {
        // Generate initial RSA key
        rsaKey = rsaKeyGenerator.generateRsa();
    }

    @Override
    public List<JWK> getJwks() {
        return Arrays.asList(rsaKey);
    }

}
