package oidc.management.service.impl;

import oidc.management.model.UserAccount;
import oidc.management.repository.UserAccountRepository;
import oidc.management.service.UserAccountEncryptionService;
import oidc.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DefaultUserAccountService implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountEncryptionService userAccountEncryptionService;

    @Override
    public List<UserAccount> findAll() {
        // Find all user accounts
        return this.userAccountRepository.findAll()
                .stream()
                .map(
                        // Decrypt user accounts
                        userAccount -> this.userAccountEncryptionService.decrypt(userAccount)
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
                        userAccount -> this.userAccountEncryptionService.decrypt(userAccount)
                );
    }

    @Override
    public Optional<UserAccount> findById(String id) {
        // Find user account by id
        return this.userAccountRepository.findById(id)
                .map(
                        // Decrypt user account
                        userAccount -> this.userAccountEncryptionService.decrypt(userAccount)
                );
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        // TODO: Add logic to find user account by encrypted username
        // Find user account by username
        return this.userAccountRepository.findByUsername(username)
                .map(
                        // Decrypt user account
                        userAccount -> this.userAccountEncryptionService.decrypt(userAccount)
                );
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        // Encrypt user account before saving
        return this.userAccountRepository.save(
                this.userAccountEncryptionService.encrypt(userAccount)
        );
    }

    @Override
    public void deleteById(String id) {
        this.userAccountRepository.deleteById(id);
    }

}
