package oidc.management.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import lombok.extern.java.Log;
import oidc.management.model.Scope;
import oidc.management.properties.DefaultScopeProperties;
import oidc.management.repository.ScopeRepository;

/**
 * Creates default scopes.
 * 
 * @author Matias Hermosilla
 * @since 11-03-2022
 * @see DefaultScopeProperties
 */
@Log
@ConditionalOnProperty(prefix = "oidc.management.scope", name = "enabled", havingValue = "true")
@ConditionalOnBean(DefaultScopeProperties.class)
public class DefaultScopeSeeder {

    @Autowired
    private ScopeRepository scopeRepository;

    protected static final List<Scope> DEFAULT_SCOPES = Arrays.asList(
        new Scope(null, "openid", "OpenID Connect"),
        new Scope(null, "profile", "User profile"),
        new Scope(null, "email", "User email"),
        new Scope(null, "address", "User address"),
        new Scope(null, "phone", "User phone"),
        new Scope(null, "offline_access", "Offline access"),
        new Scope(null, "create_authority", "Create authorities"),
        new Scope(null, "read_authority", "Read authorities"),
        new Scope(null, "update_authority", "Update authorities"),
        new Scope(null, "delete_authority", "Delete authorities"),
        new Scope(null, "create_service_account", "Create service accounts"),
        new Scope(null, "read_service_account", "Read service accounts"),
        new Scope(null, "update_service_account", "Update service accounts"),
        new Scope(null, "delete_service_account", "Delete service accounts"),
        new Scope(null, "create_user_account", "Create User accounts"),
        new Scope(null, "read_user_account", "Read User accounts"),
        new Scope(null, "update_user_account", "Update User accounts"),
        new Scope(null, "delete_user_account", "Delete User accounts"),
        new Scope(null, "create_scope", "Create scopes"),
        new Scope(null, "read_scope", "Read scopes"),
        new Scope(null, "update_scope", "Update scopes"),
        new Scope(null, "delete_scope", "Delete scopes")
    );

    @PostConstruct
    public void seed() {

        // For each default scope
        for (Scope scope : DEFAULT_SCOPES) {

            // Create the scope if it does not exist
            createIfNotExists(scope);

        }

    }

    /**
     * Creates default scopes.
     * 
     * @param name The name of the scope
     * @param description The description of the scope
     */
    public void createIfNotExists(Scope scope) {
        
        // Find if the scope already exists
        Optional<Scope> optScope = scopeRepository.findByName(scope.getName());

        // If the scope does not exist, create it
        if (!optScope.isPresent()) {

            // Save the scope
            this.scopeRepository.save(scope);

            // Log the creation of the scope
            log.info("Created default scope \"" + scope.getName() + "\"");

        } else {

            // Log the scope already exists
            log.info("Default scope \"" + scope.getName() + "\" already exists");

            // Set the scope id
            scope.setId(optScope.get().getId());

        }

    }
    
}
