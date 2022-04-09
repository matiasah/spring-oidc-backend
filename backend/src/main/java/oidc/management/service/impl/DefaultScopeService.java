package oidc.management.service.impl;

import oidc.management.model.Scope;
import oidc.management.repository.ScopeRepository;
import oidc.management.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of {@link ScopeService} for managing scopes.
 *
 * @author Matías Hermosilla
 * @since 09-04-2022
 */
public class DefaultScopeService implements ScopeService {

    @Autowired
    private ScopeRepository scopeRepository;

    @Override
    public List<Scope> findAll() {
        // Find all scopes
        return scopeRepository.findAll();
    }

    @Override
    public Page<Scope> findAll(Pageable pageable, String search) {

        // If there is no search term or it is empty
        if (search == null || search.isEmpty()) {

            // Return all scopes
            return scopeRepository.findAll(pageable);
        }

        // Return all scopes that match the search term
        return scopeRepository.findAll(pageable);
    }

    @Override
    public Optional<Scope> findById(String id) {
        // Find scope by id
        return scopeRepository.findById(id);
    }

    @Override
    public Scope save(Scope scope) {
        // ESave the scope
        return scopeRepository.save(scope);
    }

    @Override
    public void deleteById(String id) {
        // Delete the scope by id
        scopeRepository.deleteById(id);
    }

    @Override
    public Optional<Scope> findByName(String name) {
        // Find a scope by its name
        return scopeRepository.findByName(name);
    }

}
