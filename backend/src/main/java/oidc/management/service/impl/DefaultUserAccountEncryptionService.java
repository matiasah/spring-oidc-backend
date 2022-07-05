package oidc.management.service.impl;

import oidc.management.model.UserAccount;
import oidc.management.service.UserAccountEncryptionService;

/**
 * Default implementation of {@link UserAccountEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
public class DefaultUserAccountEncryptionService implements UserAccountEncryptionService {

    @Override
    public UserAccount encrypt(UserAccount userAccount) {
        // Set hashed username
        userAccount.setHashedUsername(userAccount.getUsername());

        return userAccount;
    }

    @Override
    public UserAccount decrypt(UserAccount userAccount) {
        return userAccount;
    }

    @Override
    public String getHashedUsername(String username) {
        return username;
    }

}
