package oidc.management.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.ServiceAccount;

/**
 * OIDC Service Account Repository.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see ServiceAccount
 */
public interface ServiceAccountRepository extends MongoRepository<ServiceAccount, String> {

    /**
     * Finds a client by client id.
     */
    public Optional<ServiceAccount> findByClientId(String clientId);
    
}
