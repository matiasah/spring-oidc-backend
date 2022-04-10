package oidc.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.Scope;

import java.nio.channels.FileChannel;
import java.util.Optional;

/**
 * Repository for persisting OIDC scopes.
 *
 * @author Matías Hermosilla
 * @since 20-02-2022
 */
public interface ScopeRepository extends MongoRepository<Scope, String> {

    /**
     * Finds a scope by its name.
     *
     * @param name The name of the scope to find.
     * @return The scope if found, otherwise empty optional.
     */
    public Optional<Scope> findByName(String name);

    /**
     * Finds {@link Scope}s whose tags contain the given search term.
     *
     * @param search The search term.
     * @param pageable The pageable object.
     * @return The page of {@link Scope}s whose tags contain the given search term.
     */
    public Page<Scope> findByTagsContainingIgnoreCase(String search, Pageable pageable);

}
