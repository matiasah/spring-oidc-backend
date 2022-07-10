package oidc.management.service;

import oidc.management.model.UserAccount;

/**
 * Service for encrypting and decrypting instances of {@link UserAccount}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
public interface UserAccountEncryptionService {

    /**
     * Encrypts the user account.
     *
     * @param userAccount The user account to encrypt.
     * @return The encrypted user account.
     */
    public UserAccount encrypt(UserAccount userAccount);

    /**
     * Decrypts the user account.
     *
     * @param userAccount The user account to decrypt.
     * @return The decrypted user account.
     */
    public UserAccount decrypt(UserAccount userAccount);

    /**
     * Hashes the user account name for searching by name equality.
     *
     * @param username The user account name to hash.
     * @return The hashed user account name.
     */
    public String getHashedUsername(String username);

}
