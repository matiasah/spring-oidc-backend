package oidc.management.service.impl;

import oidc.management.model.Scope;
import oidc.management.service.ScopeEncryptionService;

/**
 * Default implementation of {@link ScopeEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 09-04-2022
 */
public class DefaultScopeEncryptionService implements ScopeEncryptionService {

    /**
     * Default encrypt behaviour, does not encrypt.
     *
     * @param scope The {@link Scope} that should be encrypted.
     * @return The {@link Scope} as it is.
     */
    @Override
    public Scope encrypt(Scope scope) {
        // Copy name to hashedName property
        scope.setHashedName(scope.getName());

        return scope;
    }

    /**
     * Default decrypt behaviour, does not decrypt.
     *
     * @param scope The {@link Scope} that should be decrypted.
     * @return The {@link Scope} as it is.
     */
    @Override
    public Scope decrypt(Scope scope) {
        return scope;
    }

    /**
     * Default hash behaviour, does not hash.
     *
     * @param name The name of the {@link Scope} that should be hashed.
     * @return The name of the {@link Scope} as it is.
     */
    @Override
    public String getHashedName(String name) {
        return name;
    }

}
