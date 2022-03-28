package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountEncryptionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * Default implementation of {@link ServiceAccountEncryptionService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
@ConditionalOnMissingBean(ServiceAccountEncryptionService.class)
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
