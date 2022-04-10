package oidc.management.service;

import oidc.management.model.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing authorities.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
public interface AuthorityService {

    /**
     * Finds all {@link Authority}s.
     *
     * @return List of {@link Authority}s.
     */
    public List<Authority> findAll();

    /**
     * Finds a page of {@link Authority}s.
     *
     * @param pageable Pageable object.
     * @param search The search string.
     * @return Page of {@link Authority}s.
     */
    public Page<Authority> findAll(Pageable pageable, String search);

    /**
     * Finds an {@link Authority} by its id.
     *
     * @param id The id of the {@link Authority}.
     * @return The {@link Authority}.
     */
    public Optional<Authority> findById(String id);

    /**
     * Finds an {@link Authority} by its name.
     *
     * @param name The name of the {@link Authority}.
     * @return The {@link Authority}.
     */
    public Optional<Authority> findByName(String name);

    /**
     * Saves an {@link Authority}.
     *
     * @param authority The {@link Authority} to save.
     * @return The saved {@link Authority}.
     */
    public Authority save(Authority authority);

    /**
     * Deletes an {@link Authority} by its id.
     *
     * @param id The id of the {@link Authority}.
     */
    public void deleteById(String id);

}
