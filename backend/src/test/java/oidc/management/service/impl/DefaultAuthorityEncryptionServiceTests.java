package oidc.management.service.impl;

import oidc.management.model.Authority;
import oidc.management.service.AuthorityEncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        DefaultAuthorityEncryptionService.class
})
public class DefaultAuthorityEncryptionServiceTests {

    @Autowired
    private AuthorityEncryptionService authorityEncryptionService;

    @Mock
    private Authority authority;

    @Test
    public void testEncrypt() {
        // Test encrypt
        Assertions.assertNotNull(authorityEncryptionService.encrypt(authority));
    }

    @Test
    public void testDecrypt() {
        // Test decrypt
        Assertions.assertNotNull(authorityEncryptionService.decrypt(authority));
    }

    @Test
    public void testGetHashedName() {
        // Test getHashedName
        final String hashedName = authorityEncryptionService.getHashedName("test");

        // Validate
        Assertions.assertNotNull(hashedName);
    }

}
