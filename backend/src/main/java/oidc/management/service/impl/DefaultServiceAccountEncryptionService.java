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

    @Override
    public ServiceAccount encrypt(ServiceAccount serviceAccount) {
        return serviceAccount;
    }

    @Override
    public ServiceAccount decrypt(ServiceAccount serviceAccount) {
        return serviceAccount;
    }

}
