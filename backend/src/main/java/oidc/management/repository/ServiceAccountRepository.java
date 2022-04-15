package oidc.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import oidc.management.model.ServiceAccount;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * OIDC Service Account Repository.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see PagingAndSortingRepository
 * @see ServiceAccount
 */
@NoRepositoryBean
public interface ServiceAccountRepository extends PagingAndSortingRepository<ServiceAccount, String> {

    public List<ServiceAccount> findAll();

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
