package oidc.management.service.impl;

import lombok.extern.log4j.Log4j2;
import oidc.management.model.Scope;
import oidc.management.repository.ScopeRepository;
import oidc.management.service.ScopeEncryptionService;
import oidc.management.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link ScopeService} for managing scopes.
 *
 * @author Matías Hermosilla
 * @since 09-04-2022
 */
@Log4j2
public class DefaultScopeService implements ScopeService {

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    private ScopeEncryptionService scopeEncryptionService;

    @Override
    public Scope.ScopeBuilder entityBuilder() {
        return scopeRepository.entityBuilder();
    }

    @Override
    public List<Scope> findAll() {
        // Find all scopes
        return (List<Scope>) scopeRepository.findAll()
                .stream()
                .map(
                        // Decrypt scope
                        scope -> scopeEncryptionService.decrypt((Scope) scope)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Page<Scope> findAll(Pageable pageable, String search) {

        // If there is no search term or the search term is empty
        if (search == null || search.isEmpty()) {

            // Return all scopes
            return scopeRepository.findAll(pageable)
                    .map(
                            // Decrypt scope
                            scope -> scopeEncryptionService.decrypt((Scope) scope)
                    );
        }

        // Return all scopes that match the search term
        return scopeRepository.findByTagsContainingIgnoreCase(search, pageable)
                .map(
                        // Decrypt scope
                        scope -> scopeEncryptionService.decrypt((Scope) scope)
                );
    }

    @Override
    public Optional<Scope> findById(String id) {
        // Find scope by id
        return scopeRepository.findById(id)
                .map(
                        // Decrypt scope
                        scope -> scopeEncryptionService.decrypt((Scope) scope)
                );
    }

    @Override
    public Optional<Scope> findByName(String name) {
        // Find a scope by its name
        return scopeRepository.findByHashedName(scopeEncryptionService.getHashedName(name))
                .map(
                        // Decrypt scope
                        scope -> scopeEncryptionService.decrypt((Scope) scope)
                );
    }

    @Override
    public Scope save(Scope scope) {
        // Encrypt scope
        Scope encryptedScope = scopeEncryptionService.encrypt(scope);

        try {
            // Save scope
            this.scopeRepository.save(encryptedScope);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems saving scope", e);
        }

        // Set id
        scope.setId(encryptedScope.getId());

        // Return scope
        return scope;
    }

    @Override
    public void deleteById(String id) {
        try {
            // Delete the scope by id
            scopeRepository.deleteById(id);
        } catch (DataAccessResourceFailureException e) {
            // Failed to read response from database
            log.warn("Problems deleting scope", e);
        }
    }

}
