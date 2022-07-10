package oidc.management.service.impl;

import oidc.management.model.AuditEvent;
import oidc.management.service.AuditEventEncryptionService;

/**
 * Default implementation of {@link AuditEventEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 10-07-2022
 */
public class DefaultAuditEventEncryptionService implements AuditEventEncryptionService {

    /**
     * Default encrypt behaviour, does not encrypt.
     *
     * @param auditEvent The {@link AuditEvent} that should be encrypted.
     * @return The {@link AuditEvent} as is.
     */
    @Override
    public AuditEvent encrypt(AuditEvent auditEvent) {
        // Return object as is.
        return auditEvent;
    }

    /**
     * Default decrypt behaviour, does not decrypt.
     *
     * @param auditEvent The {@link AuditEvent} that should be decrypted.
     * @return The {@link AuditEvent} as is.
     */
    @Override
    public AuditEvent decrypt(AuditEvent auditEvent) {
        // Return object as is.
        return auditEvent;
    }

}
