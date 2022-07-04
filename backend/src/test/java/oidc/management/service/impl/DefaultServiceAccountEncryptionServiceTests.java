package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountEncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        DefaultServiceAccountEncryptionService.class
})
public class DefaultServiceAccountEncryptionServiceTests {

    @Autowired
    private ServiceAccountEncryptionService serviceAccountEncryptionService;

    @Mock
    private ServiceAccount serviceAccount;

    @Test
    public void testEncrypt() {
        // Test encrypt
        Assertions.assertNotNull(serviceAccountEncryptionService.encrypt(serviceAccount));
    }

    @Test
    public void testDecrypt() {
        // Test decrypt
        Assertions.assertNotNull(serviceAccountEncryptionService.decrypt(serviceAccount));
    }

    @Test
    public void testGetHashedClientId() {
        // Test getHashedClientId
        final String hashedClientId = serviceAccountEncryptionService.getHashedClientId("test");

        // Validate
        Assertions.assertNotNull(hashedClientId);
    }

}
