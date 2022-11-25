package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import oidc.management.jwk.JwkProvider;
import oidc.management.jwk.RSAKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import jakarta.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * JWK provider that changes the JWKs it provides over time.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
public class RenewingJwkProvider implements JwkProvider {

    /**
     * Maximum number of JWKs to be generated.
     */
    @Value("${oidc.management.jwk.renewing.limit}")
    private int limit;

    /**
     * RSA Key Generator used to generate the JWK.
     */
    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    /**
     * List of current JWKs.
     */
    private final List<JWK> jwks = new LinkedList<>();

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

    @Scheduled(cron = "${oidc.management.jwk.renewing.cron}")
    public void renew() {
        // Generate new RSA key
        RSAKey rsaKey = rsaKeyGenerator.generateRsa();

        // If the list is full
        if (jwks.size() >= limit) {

            // Remove the last element
            jwks.remove(jwks.size() - 1);
        }

        // Add the new key at the beginning of the list
        jwks.add(0, rsaKey);
    }

}
