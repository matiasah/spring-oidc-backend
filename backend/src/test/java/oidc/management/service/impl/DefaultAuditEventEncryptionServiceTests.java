package oidc.management.service.impl;

import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventEncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        DefaultAuditEventEncryptionService.class
})
public class DefaultAuditEventEncryptionServiceTests {

    @Autowired
    private AuditEventEncryptionService authorityEncryptionService;

    @Mock
    private AuditEvent auditEvent;

    @Test
    public void testEncrypt() {
        // Test encrypt
        Assertions.assertNotNull(authorityEncryptionService.encrypt(auditEvent));
    }

    @Test
    public void testDecrypt() {
        // Test decrypt
        Assertions.assertNotNull(authorityEncryptionService.decrypt(auditEvent));
    }

}
