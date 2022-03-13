package oidc.management.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;
import lombok.extern.java.Log;
import oidc.management.model.Authority;
import oidc.management.model.Scope;
import oidc.management.model.ServiceAccount;
import oidc.management.properties.DefaultServiceAccountProperties;
import oidc.management.repository.ScopeRepository;
import oidc.management.repository.ServiceAccountRepository;

/**
 * Creates a default service account.
 *
 * @author Matias Hermosilla
 * @see DefaultServiceAccountProperties
 * @since 20-02-2022
 */
@Log
@Component("defaultServiceAccountSeeder")
@DependsOn("defaultScopeSeeder")
@ConditionalOnProperty(prefix = "oidc.management.default.service-account", name = "enabled", havingValue = "true")
@ConditionalOnBean({DefaultServiceAccountProperties.class, DefaultScopeSeeder.class})
public class DefaultServiceAccountSeeder {

    @Autowired
    private DefaultServiceAccountProperties serviceAccountProperties;

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a default service account.
     */
    @PostConstruct
    public void seed() {

        // If the service account has no redirect uris
        if (serviceAccountProperties.getRedirectUris() == null
                || serviceAccountProperties.getRedirectUris().isEmpty()) {

            // Warn
            log.warning("The service account has no redirect uris. The service account will not be created.");

            // Stop execution of the seeder
            return;

        }

        // List of scopes
        List<Scope> scopes = new ArrayList<>();

        // For each default scope
        for (Scope scope : DefaultScopeSeeder.DEFAULT_SCOPES) {

            // Find the scope by name
            Optional<Scope> optionalScope = scopeRepository.findByName(scope.getName());

            // If the scope exists
            if (optionalScope.isPresent()) {

                // Add the scope to the list
                scopes.add(optionalScope.get());

            }

        }

        // If the list of scopes has equal amount of scopes as the default scopes
        if (scopes.size() != DefaultScopeSeeder.DEFAULT_SCOPES.size()) {

            // Warn
            log.warning("The number of stored scopes is not equal to the number default scopes. The service account will not be created");

            // Stop execution of the seeder
            return;

        }

        // Get client Id of the service account
        String clientId = serviceAccountProperties.getClientId();

        // Find if the service account already exists
        Optional<ServiceAccount> optServiceAccount = serviceAccountRepository.findByClientId(clientId);

        // If the service account does not exist, create it
        if (optServiceAccount.isPresent()) {

            // Info
            log.info("The default service account \"" + serviceAccountProperties.getClientId() + "\" already exists");

            // Stop execution of the seeder
            return;

        }

        // Create the service account object
        ServiceAccount serviceAccount = ServiceAccount.builder()
                .clientId(serviceAccountProperties.getClientId())
                .clientSecret(this.passwordEncoder.encode(serviceAccountProperties.getClientSecret()))
                .clientSecretExpiresAt(null)
                .clientName("Default Service Account")
                .clientDescription("Generated by first initialization")
                .clientAuthenticationMethods(Arrays
                        .asList(ClientAuthenticationMethod.CLIENT_SECRET_BASIC,
                                ClientAuthenticationMethod.CLIENT_SECRET_POST)
                        .stream().collect(Collectors.toSet()))
                .authorizationGrantTypes(Arrays
                        .asList(AuthorizationGrantType.AUTHORIZATION_CODE,
                                AuthorizationGrantType.REFRESH_TOKEN)
                        .stream().collect(Collectors.toSet()))
                .redirectUris(serviceAccountProperties.getRedirectUris())
                .scopes(scopes.stream().collect(Collectors.toSet()))
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().build())
                .build();

        // Save the service account
        this.serviceAccountRepository.save(serviceAccount);

        // Log
        log.info("Created a default service account with id \"" + serviceAccount.getClientId() + "\" and password \"" + serviceAccountProperties.getClientSecret() + "\"");

    }

}
