package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import oidc.management.jwk.RSAKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {
        VaultJwkProvider.class,
        RSAKeyGenerator.class
})
public class VaultJwkProviderTests {

    /**
     * Path to the JWKs secret in Vault.
     */
    @Value("${oidc.management.jwk.vault.path}")
    private String path;

    @Lazy
    @Autowired
    private VaultJwkProvider vaultJwkProvider;

    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    @MockBean
    private VaultOperations vaultOperations;

    @Mock
    private VaultKeyValueOperations vaultKeyValueOperations;

    @Mock
    private VaultResponse vaultResponse;

    @Test
    public void testGetJwks() {

        // Create a list of keys
        List<String> listOfKeys = Arrays.asList("jwk");

        // Generate JWK
        JWK jwk = rsaKeyGenerator.generateRsa();

        // Convert to Map
        Map<String, Object> map = jwk.toJSONObject();

        // Mock opsForKeyValue
        Mockito.when(vaultOperations.opsForKeyValue(path, VaultKeyValueOperationsSupport.KeyValueBackend.KV_1))
                .thenReturn(vaultKeyValueOperations);

        // Mock list
        Mockito.when(vaultKeyValueOperations.list("")).thenReturn(listOfKeys);

        // Mock get
        Mockito.when(vaultKeyValueOperations.get("jwk")).thenReturn(vaultResponse);

        // Mock getData
        Mockito.when(vaultResponse.getData()).thenReturn(map);

        // Test getJwks
        List<JWK> jwks = vaultJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(1, jwks.size());

    }

    @Test
    public void testGetJwksWithNullData() {

        // Create a list of keys
        List<String> listOfKeys = Arrays.asList("jwk");

        // Mock opsForKeyValue
        Mockito.when(vaultOperations.opsForKeyValue(path, VaultKeyValueOperationsSupport.KeyValueBackend.KV_1))
                .thenReturn(vaultKeyValueOperations);

        // Mock list
        Mockito.when(vaultKeyValueOperations.list("")).thenReturn(listOfKeys);

        // Test getJwks
        List<JWK> jwks = vaultJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(0, jwks.size());

    }

    @Test
    public void testGetJwksWithInvalidData() {

        // Create a list of keys
        List<String> listOfKeys = Arrays.asList("jwk");

        // Convert to Map
        Map<String, Object> map = Map.of("ABC", "123");

        // Mock opsForKeyValue
        Mockito.when(vaultOperations.opsForKeyValue(path, VaultKeyValueOperationsSupport.KeyValueBackend.KV_1))
                .thenReturn(vaultKeyValueOperations);

        // Mock list
        Mockito.when(vaultKeyValueOperations.list("")).thenReturn(listOfKeys);

        // Mock get
        Mockito.when(vaultKeyValueOperations.get("jwk")).thenReturn(vaultResponse);

        // Mock getData
        Mockito.when(vaultResponse.getData()).thenReturn(map);

        // Test getJwks
        List<JWK> jwks = vaultJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(0, jwks.size());

    }

}
