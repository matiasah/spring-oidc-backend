package oidc.management.service.impl;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.UserAccount;
import oidc.management.repository.UserAccountRepository;
import oidc.management.service.UserAccountEncryptionService;
import oidc.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link UserAccountService}.
 *
 * @author Matías Hermosilla
 * @since 27-03-2022
 */
@Log4j2
public class DefaultUserAccountService implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountEncryptionService userAccountEncryptionService;

    @Override
    public UserAccount.UserAccountBuilder entityBuilder() {
        return userAccountRepository.entityBuilder();
    }

    @Override
    public List<UserAccount> findAll() {
        // Find all user accounts
        return (List<UserAccount>) this.userAccountRepository.findAll()
                .stream()
                .map(
                        // Decrypt user accounts
                        userAccount -> this.userAccountEncryptionService.decrypt((UserAccount) userAccount)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserAccount> findAll(Pageable pageable, String search) {

        // If there is no search term or the search term is empty
        if (search == null || search.isEmpty()) {

            // Return all user accounts
            return this.userAccountRepository.findAll(pageable);
        }

        // Return all user accounts that match the search term
        return this.userAccountRepository.findByTagsContainingIgnoreCase(search, pageable)
                .map(
                        // Decrypt user accounts
                        userAccount -> this.userAccountEncryptionService.decrypt((UserAccount) userAccount)
                );
    }

    @Override
    public Optional<UserAccount> findById(String id) {
        // Find user account by id
        return this.userAccountRepository.findById(id)
                .map(
                        // Decrypt user account
                        userAccount -> this.userAccountEncryptionService.decrypt((UserAccount) userAccount)
                );
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        // Find user account by username
        return this.userAccountRepository.findByHashedUsername(this.userAccountEncryptionService.getHashedUsername(username))
                .map(
                        // Decrypt user account
                        userAccount -> this.userAccountEncryptionService.decrypt((UserAccount) userAccount)
                );
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        // Encrypt user account
        UserAccount encryptedUserAccount = this.userAccountEncryptionService.encrypt(userAccount);

        try {
            // Save user account
            this.userAccountRepository.save(encryptedUserAccount);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems saving user account", e);
        }

        // Set id
        userAccount.setId(encryptedUserAccount.getId());

        // Return user account
        return userAccount;
    }

    @Override
    public void deleteById(String id) {
        try {
            // Delete user account by id
            this.userAccountRepository.deleteById(id);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems deleting user account", e);
        }
    }

}
