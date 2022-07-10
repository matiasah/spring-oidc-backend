package oidc.management.service;

import oidc.management.model.Scope;

/**
 * Service for encrypting and decrypting instances of {@link Scope}.
 *
 * @author Matías Hermosilla
 * @since 09-04-2022
 */
public interface ScopeEncryptionService {

    /**
     * Encrypts the given {@link Scope} and returns the encrypted version.
     *
     * @param scope The {@link Scope} to encrypt.
     * @return The encrypted version of the given {@link Scope}.
     */
    public Scope encrypt(Scope scope);

    /**
     * Decrypts the given {@link Scope} and returns the decrypted version.
     *
     * @param scope The {@link Scope} to decrypt.
     * @return The decrypted version of the given {@link Scope}.
     */
    public Scope decrypt(Scope scope);

    /**
     * Hashes the name of the given {@link Scope} and returns the hashed version.
     *
     * @param name The name of the {@link Scope} to hash.
     * @return The hashed version of the given name.
     */
    public String getHashedName(String name);

}
