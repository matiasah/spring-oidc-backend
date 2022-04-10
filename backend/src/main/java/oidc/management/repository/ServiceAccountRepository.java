package oidc.management.repository;

import java.util.Optional;

import oidc.management.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.ServiceAccount;

/**
 * OIDC Service Account Repository.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 */
public interface ServiceAccountRepository extends MongoRepository<ServiceAccount, String> {

    /**
     * Finds a client by client id.
     */
    public Optional<ServiceAccount> findByHashedClientId(String clientId);

    /**
     * Finds {@link ServiceAccount}s whose tags contain the given search term.
     *
     * @param tag The search term.
     * @param pageable The pageable object.
     * @return A page of {@link ServiceAccount} whose tags contain the given search term.
     */
    public Page<ServiceAccount> findByTagsContainingIgnoreCase(String tag, Pageable pageable);
    
}
