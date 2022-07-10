package oidc.management.service;

import oidc.management.model.AuditEvent;

/**
 * Service for encrypting and decrypting instances of {@link oidc.management.model.AuditEvent}.
 *
 * @author Matías Hermosilla
 * @since 10-07-2022
 */
public interface AuditEventEncryptionService {

    /**
     * Encrypts the given {@link AuditEvent} and returns the encrypted version.
     *
     * @param auditEvent The {@link AuditEvent} to encrypt.
     * @return The encrypted {@link AuditEvent}.
     */
    public AuditEvent encrypt(AuditEvent auditEvent);

    /**
     * Decrypts the given {@link AuditEvent} and returns the decrypted version.
     *
     * @param auditEvent The {@link AuditEvent} to decrypt.
     * @return The decrypted {@link AuditEvent}.
     */
    public AuditEvent decrypt(AuditEvent auditEvent);
    
}
