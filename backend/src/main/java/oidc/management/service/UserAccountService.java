package oidc.management.service;

import oidc.management.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * User account service.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 * @see UserAccount
 * @see oidc.management.service.impl.DefaultUserAccountService
 */
public interface UserAccountService {

    public UserAccount.UserAccountBuilder entityBuilder();

    /**
     * Find all the {@link UserAccount}s.
     *
     * @return List of {@link UserAccount}s.
     */
    public List<UserAccount> findAll();

    /**
     * Find a page of @{@link UserAccount}s.
     *
     * @param pageable Pageable object.
     * @param search Search string.
     * @return Page of {@link UserAccount}s.
     */
    public Page<UserAccount> findAll(Pageable pageable, String search);

    /**
     * Find a {@link UserAccount} by its id.
     *
     * @param id The {@link UserAccount} id.
     * @return An {@link Optional} containing the {@link UserAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<UserAccount> findById(String id);

    /**
     * Find a user account by its username.
     *
     * @param username Username.
     * @return An {@link Optional} containing the {@link UserAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<UserAccount> findByUsername(String username);

    /**
     * Save a {@link UserAccount}.
     *
     * @param userAccount The {@link UserAccount} to save.
     * @return The saved {@link UserAccount}.
     */
    public UserAccount save(UserAccount userAccount);

    /**
     * Deletes a {@link UserAccount} by it's {@link String} id.
     *
     * @param id The {@link UserAccount} id.
     */
    public void deleteById(String id);

}
