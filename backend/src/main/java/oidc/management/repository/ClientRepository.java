package oidc.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.Client;

/**
 * OIDC Client Repository.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see Client
 */
public interface ClientRepository extends MongoRepository<Client, String> {
    
}
