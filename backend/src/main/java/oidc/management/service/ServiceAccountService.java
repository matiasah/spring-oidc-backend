package oidc.management.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Service;
import oidc.management.model.Authority;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;

/**
 * ServiceAccount Service for managing Service Accounts.
 * 
 * @author Matías Hermosilla
 * @since 03-02-2022
 * @see ServiceAccount
 * @see ServiceAccountRepository
 */
@Service
public class ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     * 
     * @param id The {@link String} identifier.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findById(String id) {

        // Development service account
        if (id.equals("dev")) {
            return this.findByClientId(id);
        }

        return serviceAccountRepository.findById(id);
    }

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     * 
     * @param clientId The {@link String} clientId.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findByClientId(String clientId) {

        // Development service account
        if (clientId.equals("dev")) {
            return Optional.of(ServiceAccount.builder()
                .id("dev")
                .clientId(clientId)
                .clientIdIssuedAt(Instant.now())
                .clientSecret(passwordEncoder.encode("dev"))
                .clientSecretExpiresAt(null)
                .clientName("dev")
                .clientAuthenticationMethods(
                    Arrays.asList(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, ClientAuthenticationMethod.CLIENT_SECRET_POST)
                        .stream()
                        .collect(Collectors.toSet())
                    )
                .authorizationGrantTypes(
                    Arrays.asList(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.REFRESH_TOKEN, AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .stream()
                        .collect(Collectors.toSet())
                    )
                .redirectUris(
                    Arrays.asList(
                        "http://127.0.0.1/oauth/callback",
                        "http://localhost/oauth/callback",
                        "https://openidconnect.net/callback")
                        .stream()
                        .collect(Collectors.toSet())
                )
                .scopes(
                    Arrays.asList(OidcScopes.OPENID, OidcScopes.PROFILE)
                        .stream()
                        .collect(Collectors.toSet())
                )
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().build())
                .authorities(Arrays.asList(Authority.builder().name("ROLE_SERVICE_ACCOUNT").build()))
                .build());
        }

        return serviceAccountRepository.findByClientId(clientId);
    }
    
}
