package oidc.management.service.impl;

import oidc.management.model.UserAccount;
import oidc.management.service.UserAccountEncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        DefaultUserAccountEncryptionService.class
})
public class DefaultUserAccountEncryptionServiceTests {

    @Autowired
    private UserAccountEncryptionService userAccountEncryptionService;

    @Mock
    private UserAccount userAccount;

    @Test
    public void testEncrypt() {

        // Test encrypt
        Assertions.assertNotNull(userAccountEncryptionService.encrypt(userAccount));

    }

    @Test
    public void testDecrypt() {

        // Test decrypt
        Assertions.assertNotNull(userAccountEncryptionService.decrypt(userAccount));

    }

    @Test
    public void testGetHashedName() {

        // Test getHashedName
        final String hashedName = userAccountEncryptionService.getHashedUsername("test");

        // Validate
        Assertions.assertNotNull(hashedName);

    }

}
