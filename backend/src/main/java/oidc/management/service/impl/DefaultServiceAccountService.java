package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.Optional;

/**
 * Default implementation of {@link ServiceAccountService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
@ConditionalOnMissingBean(ServiceAccountService.class)
public class DefaultServiceAccountService implements ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     *
     * @param id The {@link String} identifier.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findById(String id) {

        // Find Service Account by id
        return serviceAccountRepository.findById(id);
    }

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     *
     * @param clientId The {@link String} clientId.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findByClientId(String clientId) {

        // Find Service Account by clientId
        return serviceAccountRepository.findByClientId(clientId);
    }

}
