package oidc.management.service;

import java.util.Optional;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;

/**
 * ServiceAccount Service for managing Service Accounts.
 * 
 * @author Matías Hermosilla
 * @since 03-02-2022
 * @see ServiceAccount
 * @see ServiceAccountRepository
 */
public interface ServiceAccountService {

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     * 
     * @param id The {@link String} identifier.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findById(String id);

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     * 
     * @param clientId The {@link String} clientId.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findByClientId(String clientId);
    
}
