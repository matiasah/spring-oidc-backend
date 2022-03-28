package oidc.management.service;

import java.util.List;
import java.util.Optional;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Find all Service Accounts.
     *
     * @return A list of Service Accounts.
     */
    public List<ServiceAccount> findAll();

    /**
     * Find a page of Service Accounts.
     *
     * @param pageable Pageable object.
     * @return A page of Service Accounts.
     */
    public Page<ServiceAccount> findAll(Pageable pageable);

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

    /**
     * Saves a {@link ServiceAccount}.
     *
     * @param serviceAccount The {@link ServiceAccount} to save.
     * @return The saved {@link ServiceAccount}.
     */
    public ServiceAccount save(ServiceAccount serviceAccount);

    /**
     * Deletes a {@link ServiceAccount} by it's {@link String} id.
     *
     * @param id The {@link String} id.
     */
    public void deleteById(String id);

}
