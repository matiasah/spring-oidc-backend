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
     * Encrypts the given {@link ServiceAccount}.
     *
     * @param account The {@link ServiceAccount} to encrypt.
     * @return The encrypted {@link ServiceAccount}.
     */
    public ServiceAccount encrypt(ServiceAccount account);

    /**
     * Decrypts the given {@link ServiceAccount}.
     *
     * @param account The {@link ServiceAccount} to decrypt.
     * @return The decrypted {@link ServiceAccount}.
     */
    public ServiceAccount decrypt(ServiceAccount account);

    /**
     * Hashes the given {@link ServiceAccount}'s clientId for searching by equality.
     *
     * @param clientId The clientId to hash.
     * @return The hashed clientId.
     */
    public String getHashedClientId(String clientId);

}
