package oidc.management.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import oidc.management.model.UserAccount;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see PagingAndSortingRepository
 * @see UserAccount
 * @see oidc.management.service.UserAccountService
 * @see oidc.management.service.UserAccountService#findByUsername(String)
 */
@NoRepositoryBean
public interface UserAccountRepository<T extends UserAccount> extends PagingAndSortingRepository<T, String>, CrudRepository<T, String> {

    public UserAccount.UserAccountBuilder entityBuilder();

    public List<T> findAll();

    /**
     * Finds a user by its username.
     *
     * @param username The username.
     * @return The user.
     */
    public Optional<UserAccount> findByHashedUsername(String username);

    /**
     * Finds {@link UserAccount}s whose tags contain the given search term.
     *
     * @param tag The search term.
     * @param pageable The pageable object.
     * @return A page of {@link UserAccount}s whose tags contain the given search term.
     */
    public Page<UserAccount> findByTagsContainingIgnoreCase(String tag, Pageable pageable);

}
