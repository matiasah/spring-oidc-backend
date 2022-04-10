package oidc.management.service.impl;

import oidc.management.model.Authority;
import oidc.management.repository.AuthorityRepository;
import oidc.management.service.AuthorityEncryptionService;
import oidc.management.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DefaultAuthorityService implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityEncryptionService authorityEncryptionService;

    @Override
    public List<Authority> findAll() {
        // Find all authorities
        return authorityRepository.findAll()
                .stream()
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt(authority)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<Authority> findAll(Pageable pageable, String search) {

        // If there is no search term or the search term is empty
        if (search == null || search.isEmpty()) {
            return authorityRepository.findAll(pageable);
        }

        // Find all authorities
        return authorityRepository.findAllByNameContaining(search, pageable)
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt(authority)
                );
    }

    @Override
    public Optional<Authority> findById(String id) {
        // Find authority by its id
        return authorityRepository.findById(id)
                .map(
                        // Decrypt authority
                        authority -> authorityEncryptionService.decrypt(authority)
                );
    }

    @Override
    public Authority save(Authority authority) {
        // Encrypt authority before saving it
        return authorityRepository.save(
                authorityEncryptionService.encrypt(authority)
        );
    }

    @Override
    public void deleteById(String id) {
        // Delete authority by its id
        authorityRepository.deleteById(id);
    }
}
