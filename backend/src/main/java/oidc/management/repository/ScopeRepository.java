package oidc.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.Scope;

/**
 * Repository for persisting OIDC scopes.
 *
 * @author Matías Hermosilla
 * @since 20-02-2022
 */
public interface ScopeRepository extends MongoRepository<Scope, String> {

}
