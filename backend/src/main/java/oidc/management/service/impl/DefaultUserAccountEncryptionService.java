package oidc.management.service.impl;

import oidc.management.model.UserAccount;
import oidc.management.service.UserAccountEncryptionService;
import org.springframework.stereotype.Service;

/**
 * Default implementation of {@link UserAccountEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
@Service
public class DefaultUserAccountEncryptionService implements UserAccountEncryptionService {

    @Override
    public UserAccount encrypt(UserAccount userAccount) {
        return userAccount;
    }

    @Override
    public UserAccount decrypt(UserAccount userAccount) {
        return userAccount;
    }

    @Override
    public String hashUsername(String username) {
        return username;
    }

}
