package oidc.management.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import oidc.management.model.UserAccount;

/**
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see UserAccount
 * @see oidc.management.service.UserAccountService
 * @see oidc.management.service.UserAccountService#findByUsername(String)
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

    /**
     * Finds a user by its username.
     *
     * @param username The username.
     * @return The user.
     */
    public Optional<UserAccount> findByUsername(String username);

    /**
     * Finds users whose alias contains the given search term.
     *
     * @param search The search term.
     * @param pageable The pageable object.
     * @return A page of users whose alias contains the given search term.
     */
    public Page<UserAccount> findByAliasContaining(String search, Pageable pageable);

}
