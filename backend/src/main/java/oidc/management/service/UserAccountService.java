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
 */
public interface UserAccountService {

    /**
     * Find all the user accounts.
     *
     * @return List of user accounts.
     */
    public List<UserAccount> findAll();

    /**
     * Find a page of user accounts.
     *
     * @param pageable Pageable object.
     * @return Page of user accounts.
     */
    public Page<UserAccount> findAll(Pageable pageable);

    /**
     * Find a user account by its id.
     *
     * @param id User account id.
     * @return User account.
     */
    public Optional<UserAccount> findById(String id);

    /**
     * Save a user account.
     *
     * @param userAccount User account.
     * @return User account.
     */
    public UserAccount save(UserAccount userAccount);

    /**
     * Delete a user account.
     *
     * @param id User account id.
     */
    public void deleteById(String id);

}
