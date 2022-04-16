package oidc.management.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;

import oidc.management.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import lombok.extern.java.Log;
import oidc.management.model.Scope;
import oidc.management.properties.DefaultScopeProperties;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creates default scopes.
 * 
 * @author Matias Hermosilla
 * @since 11-03-2022
 * @see DefaultScopeProperties
 */
@Log
@Component("defaultScopeSeeder")
@ConditionalOnProperty(prefix = "oidc.management.default.scope", name = "enabled", havingValue = "true")
@ConditionalOnBean(DefaultScopeProperties.class)
public class DefaultScopeSeeder {

    private static final Set<String> DEFAULT_SCOPES_TAGS = Set.of("default");

    @Autowired
    private ScopeService scopeService;

    public List<Scope> getDefaultScopes() {
        return Arrays.asList(
                scopeService.entityBuilder().name("openid").description("Verify your identity").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("email").description("See your email address").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("profile").description("See your profile information").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("address").description("See your address").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("phone").description("See your phone number").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("offline_access").description("Access your data offline").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("create_authority").description("Create authorities").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("read_authority").description("Read authorities").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("update_authority").description("Update authorities").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("delete_authority").description("Delete authorities").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("create_service_account").description("Create service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("read_service_account").description("Read service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("update_service_account").description("Update service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("delete_service_account").description("Delete service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("create_user_account").description("Create user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("read_user_account").description("Read user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("update_user_account").description("Update user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("delete_user_account").description("Delete user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("create_scope").description("Create scopes").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("read_scope").description("Read scopes").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("update_scope").description("Update scopes").tags(DEFAULT_SCOPES_TAGS).build(),
                scopeService.entityBuilder().name("delete_scope").description("Delete scopes").tags(DEFAULT_SCOPES_TAGS).build()
        );
    }

    @Transactional
    @PostConstruct
    public void seed() {

        // For each default scope
        for (Scope scope : getDefaultScopes()) {

            // Create the scope if it does not exist
            createIfNotExists(scope);

        }

    }

    /**
     * Creates default scopes.
     * 
     * @param scope The scope
     */
    public void createIfNotExists(Scope scope) {
        
        // Find if the scope already exists
        Optional<Scope> optScope = scopeService.findByName(scope.getName());

        // If the scope does not exist, create it
        if (!optScope.isPresent()) {

            // Save the scope
            this.scopeService.save(scope);

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
