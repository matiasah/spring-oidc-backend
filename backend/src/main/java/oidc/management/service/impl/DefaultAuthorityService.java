package oidc.management.service.impl;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.Authority;
import oidc.management.repository.AuthorityRepository;
import oidc.management.service.AuthorityEncryptionService;
import oidc.management.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link AuthorityService}.
 *
 * @author Matías Hermosilla
 * @since 10-04-2022
 */
@Log4j2
public class DefaultAuthorityService implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityEncryptionService authorityEncryptionService;

    @Override
    public Authority.AuthorityBuilder entityBuilder() {
        return authorityRepository.entityBuilder();
    }

    @Override
    public List<Authority> findAll() {
        // Find all authorities
        return (List<Authority>) authorityRepository.findAll()
                .stream()
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt((Authority) authority)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<Authority> findAll(Pageable pageable, String search) {

        // If there is no search term or the search term is empty
        if (search == null || search.isEmpty()) {
            return authorityRepository.findAll(pageable)
                    .map(
                            // Decrypt authority
                            authority -> authorityEncryptionService.decrypt((Authority) authority)
                    );
        }

        // Find all authorities
        return authorityRepository.findByTagsContainingIgnoreCase(search, pageable)
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt((Authority) authority)
                );
    }

    @Override
    public Optional<Authority> findById(String id) {
        // Find authority by its id
        return authorityRepository.findById(id)
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt((Authority) authority)
                );
    }

    @Override
    public Optional<Authority> findByName(String name) {
        // Find authority by its name
        return authorityRepository.findByHashedName(authorityEncryptionService.getHashedName(name))
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt((Authority) authority)
                );
    }

    @Override
    public Authority save(Authority authority) {
        // Encrypt authority
        Authority encryptedAuthority = authorityEncryptionService.encrypt(authority);

        try {
            // Save authority
            this.authorityRepository.save(encryptedAuthority);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems persisting authority", e);
        }

        // Set id
        authority.setId(encryptedAuthority.getId());

        // Return authority
        return authority;
    }

    @Override
    public void deleteById(String id) {
        try {
            // Delete authority by its id
            authorityRepository.deleteById(id);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems deleting authority", e);
        }
    }

}
