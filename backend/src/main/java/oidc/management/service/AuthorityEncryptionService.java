package oidc.management.service;

import oidc.management.model.Authority;

/**
 * Service for encrypting and decrypting instances of {@link Authority}.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
public interface AuthorityEncryptionService {

    /**
     * Encrypts the given {@link Authority} and returns the encrypted version.
     *
     * @param authority The {@link Authority} to encrypt.
     * @return The encrypted {@link Authority}.
     */
    public Authority encrypt(Authority authority);

    /**
     * Decrypts the given {@link Authority} and returns the decrypted version.
     *
     * @param authority The {@link Authority} to decrypt.
     * @return The decrypted {@link Authority}.
     */
    public Authority decrypt(Authority authority);

    /**
     * Hashes the name of the given {@link Authority} and returns the hashed version.
     *
     * @param name The name of the {@link Authority} to hash.
     * @return The hashed name.
     */
    public String getHashedName(String name);

}
