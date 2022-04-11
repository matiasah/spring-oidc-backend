package oidc.management.service.impl;

import oidc.management.model.Authority;
import oidc.management.service.AuthorityEncryptionService;

/**
 * Default implementation of {@link oidc.management.service.AuthorityEncryptionService}.
 */
public class DefaultAuthorityEncryptionService implements AuthorityEncryptionService {

    /**
     * Default encrypt behaviour, does not encrypt.
     *
     * @param authority The {@link Authority} that should be encrypted.
     * @return The {@link Authority} as is.
     */
    @Override
    public Authority encrypt(Authority authority) {
        // Set hashed name
        authority.setHashedName(authority.getName());

        return authority;
    }

    /**
     * Default decrypt behaviour, does not decrypt.
     *
     * @param authority The {@link Authority} that should be decrypted.
     * @return The {@link Authority} as is.
     */
    @Override
    public Authority decrypt(Authority authority) {
        return authority;
    }

    /**
     * Default hash behaviour, does not hash.
     *
     * @param name The name of the {@link Authority} that should be hashed.
     * @return The name of the {@link Authority} as is.
     */
    @Override
    public String getHashedName(String name) {
        return name;
    }

}
