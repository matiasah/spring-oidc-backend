package oidc.management.jwk.strategy;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.log4j.Log4j2;
import oidc.management.jwk.JwkProvider;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * JWK source that provides JWKs from a JwkProvider, and preserves an internal list of JWKs that is updated frequently.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
@Log4j2
public class ScheduledJWKSourceStrategy implements JWKSource<SecurityContext> {

    /**
     * The JWK provider.
     */
    private JwkProvider jwkProvider;

    /**
     * Current list of JWKs
     */
    private List<JWK> jwks;

    /**
     * Constructor for the class, receives a JwkProvider.
     *
     * @param jwkProvider
     */
    public ScheduledJWKSourceStrategy(JwkProvider jwkProvider) {
        // Get the JWK provider
        this.jwkProvider = jwkProvider;

        // Get the initial list of JWKs
        this.jwks = jwkProvider.getJwks();
    }

    @Override
    public List<JWK> get(final JWKSelector jwkSelector, final SecurityContext context) {
        // Return list of JWKs
        return jwks;
    }

    @Scheduled(cron = "${oidc.management.jwk.scheduled.cron}")
    public void updateJwks() {
        // Log the update
        log.info("Updating JWKs");

        // Update the list of JWKs
        jwks = jwkProvider.getJwks();

        // Log
        log.info("Updated JWKs");
    }

}
