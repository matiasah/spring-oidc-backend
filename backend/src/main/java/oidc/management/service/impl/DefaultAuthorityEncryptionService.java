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

}
