package oidc.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import oidc.management.model.Authority;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Security Authority Repository.
 * 
 * @author Matías Hermosilla
 * @since 02-02-2022
 * @see AuthorityRepository
 * @see Authority
 */
@NoRepositoryBean
public interface AuthorityRepository<T extends Authority> extends PagingAndSortingRepository<T, String>, CrudRepository<T, String> {

    public Authority.AuthorityBuilder entityBuilder();

    public List<T> findAll();

    /**
     * Finds an {@link Authority} by its hashed name.
     *
     * @param name The name of the {@link Authority}.
     * @return The {@link Authority} with the given name.
     */
    public Optional<Authority> findByHashedName(String name);

    /**
     * Finds {@link Authority}s whose tags contain the given search term.
     *
     * @param search The search term.
     * @param pageable The pageable object.
     * @return A {@link Page} of {@link Authority}s.
     */
    public Page<Authority> findByTagsContainingIgnoreCase(String search, Pageable pageable);

}
