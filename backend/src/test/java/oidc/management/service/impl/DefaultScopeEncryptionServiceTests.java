package oidc.management.service.impl;

import oidc.management.model.Scope;
import oidc.management.service.ScopeEncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        DefaultScopeEncryptionService.class
})
public class DefaultScopeEncryptionServiceTests {

    @Autowired
    private ScopeEncryptionService scopeEncryptionService;

    @Mock
    private Scope scope;

    @Test
    public void testEncrypt() {
        // Test encrypt
        Assertions.assertNotNull(scopeEncryptionService.encrypt(scope));
    }

    @Test
    public void testDecrypt() {
        // Test decrypt
        Assertions.assertNotNull(scopeEncryptionService.decrypt(scope));
    }

    @Test
    public void testGetHashedName() {
        // Test getHashedName
        final String hashedName = scopeEncryptionService.getHashedName("test");

        // Validate
        Assertions.assertNotNull(hashedName);
    }

}
