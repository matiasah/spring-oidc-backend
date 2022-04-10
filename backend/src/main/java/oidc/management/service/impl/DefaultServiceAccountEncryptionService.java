package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountEncryptionService;

/**
 * Default implementation of {@link ServiceAccountEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
public class DefaultServiceAccountEncryptionService implements ServiceAccountEncryptionService {

    /**
     * Default encrypt behaviour, does not encrypt.
     *
     * @param serviceAccount The {@link ServiceAccount} that should be encrypted.
     * @return The {@link ServiceAccount} as it is.
     */
    @Override
    public ServiceAccount encrypt(ServiceAccount serviceAccount) {
        // Set hashed client id
        serviceAccount.setHashedClientId(getHashedClientId(serviceAccount.getClientId()));

        return serviceAccount;
    }

    /**
     * Default decrypt behaviour, does not decrypt.
     *
     * @param serviceAccount The {@link ServiceAccount} that should be decrypted.
     * @return The {@link ServiceAccount} as it is.
     */
    @Override
    public ServiceAccount decrypt(ServiceAccount serviceAccount) {
        return serviceAccount;
    }

    /**
     * Default hash behaviour, does not hash.
     *
     * @param clientId The clientId that should be hashed.
     * @return The clientId as it is.
     */
    @Override
    public String getHashedClientId(String clientId) {
        return clientId;
    }

}
