package oidc.management.service;

import oidc.management.model.ServiceAccount;

/**
 * Service for encrypting and decrypting service accounts.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
public interface ServiceAccountEncryptionService {

    /**
     * Encrypts the given service account.
     *
     * @param account The account to encrypt.
     * @return The encrypted account.
     */
    public ServiceAccount encrypt(ServiceAccount account);

    /**
     * Decrypts the given service account.
     *
     * @param account The account to decrypt.
     * @return The decrypted account.
     */
    public ServiceAccount decrypt(ServiceAccount account);

}
