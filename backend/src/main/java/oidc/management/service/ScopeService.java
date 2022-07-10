package oidc.management.service;

import oidc.management.model.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing scopes.
 *
 * @author Matías Hermosilla
 * @since 09-04-2022
 */
public interface ScopeService {

    /**
     * Creates an object that is used to build instances of {@link Scope}
     *
     * @return An instance of {@link oidc.management.model.Scope.ScopeBuilder}
     */
    public Scope.ScopeBuilder entityBuilder();

    /**
     * Finds all {@link Scope}s.
     *
     * @return A list of {@link Scope}s.
     */
    public List<Scope> findAll();

    /**
     * Finds a page of {@link Scope}s.
     *
     * @param pageable The pageable.
     * @return A page of {@link Scope}s.
     */
    public Page<Scope> findAll(Pageable pageable, String search);

    /**
     * Finds a {@link Scope} by its id.
     *
     * @param id The id of the {@link Scope}.
     * @return An optional of {@link Scope}.
     */
    public Optional<Scope> findById(String id);

    /**
     * Finds a {@link Scope} by its name.
     *
     * @param name The name of the {@link Scope}.
     * @return An optional of {@link Scope}.
     */
    public Optional<Scope> findByName(String name);

    /**
     * Saves a {@link Scope}.
     *
     * @param scope The {@link Scope} to save.
     * @return The saved {@link Scope}.
     */
    public Scope save(Scope scope);

    /**
     * Deletes a {@link Scope} by its id.
     *
     * @param id The id of the {@link Scope}.
     */
    public void deleteById(String id);

}
