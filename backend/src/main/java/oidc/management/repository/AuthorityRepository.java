package oidc.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.Authority;

/**
 * Spring Security Authority Repository.
 * 
 * @author Matías Hermosilla
 * @since 02-02-2022
 * @see MongoRepository
 * @see Authority
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {

    /**
     * Finds {@link Authority}s whose tags contain the given search term.
     *
     * @param search The search term.
     * @param pageable The pageable object.
     * @return A {@link Page} of {@link Authority}s.
     */
    public Page<Authority> findByTagsContainingIgnoreCase(String search, Pageable pageable);

}
