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

    protected static final List<Scope> DEFAULT_SCOPES = Arrays.asList(
            Scope.builder().name("openid").description("Verify your identity").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("profile").description("See your profile information").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("email").description("See your email address").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("address").description("See your address").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("phone").description("See your phone number").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("offline_access").description("Access your data offline").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("create_authority").description("Create authorities").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("read_authority").description("Read authorities").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("update_authority").description("Update authorities").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("delete_authority").description("Delete authorities").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("create_service_account").description("Create service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("read_service_account").description("Read service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("update_service_account").description("Update service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("delete_service_account").description("Delete service accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("create_user_account").description("Create user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("read_user_account").description("Read user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("update_user_account").description("Update user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("delete_user_account").description("Delete user accounts").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("create_scope").description("Create scopes").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("read_scope").description("Read scopes").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("update_scope").description("Update scopes").tags(DEFAULT_SCOPES_TAGS).build(),
            Scope.builder().name("delete_scope").description("Delete scopes").tags(DEFAULT_SCOPES_TAGS).build()
    );

    @Autowired
    private ScopeService scopeService;

    public DefaultScopeSeeder() {
        // Log constructor
        log.info("Initializing default scope seeder");
    }

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
