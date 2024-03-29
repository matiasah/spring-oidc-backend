package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.log4j.Log4j2;
import oidc.management.jwk.JwkProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;

import jakarta.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * JwkProvider implementation that retrieves the JWKs from a Vault.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
@Log4j2
public class VaultJwkProvider implements JwkProvider {

    /**
     * Path to the JWKs secret in Vault.
     */
    @Value("${oidc.management.jwk.vault.path}")
    private String path;

    /**
     * Vault operations bean to retrieve the JWKs.
     */
    @Autowired
    private VaultOperations vaultOperations;

    @Override
    public List<JWK> getJwks() {

        // Get KV1 client
        VaultKeyValueOperations vaultKeyValueOperations = vaultOperations.opsForKeyValue(
                path,
                VaultKeyValueOperationsSupport.KeyValueBackend.KV_1
        );

        // List all keys in secret
        final List<String> keys = vaultKeyValueOperations.list("");

        // Initialize list of JWKs
        final List<JWK> jwks = new LinkedList<>();

        // For every key
        for (final String key : keys) {

            // Request the value
            final VaultResponse response = vaultKeyValueOperations.get(key);

            // If the response is not null
            if (response != null) {

                try {
                    // Get the data
                    Map<String, Object> data = response.getData();

                    // Construct the RSAKey
                    RSAKey rsaKey = RSAKey.parse(data);

                    // Add key to list
                    jwks.add(rsaKey);

                } catch (Exception e) {
                    // Log error
                    log.error("Error parsing JWK from Vault", e);
                }

            } else {

                // Log error
                log.error("Error retrieving JWK from Vault");

            }

        }

        // Return the list of JWKs
        return jwks;
    }

}
