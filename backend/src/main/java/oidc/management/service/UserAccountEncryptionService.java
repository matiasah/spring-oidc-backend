package oidc.management.service;

import oidc.management.model.UserAccount;

/**
 * Service for encrypting and decrypting user accounts.
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

}
