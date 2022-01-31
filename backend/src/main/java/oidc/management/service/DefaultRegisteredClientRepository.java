package oidc.management.service;

import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Service;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;

/**
 * Default implementation of {@link RegisteredClientRepository}.
 * 
 * @author Matías Hermosilla
 * @since 23-01-2022
 * @see RegisteredClientRepository
 * @see RegisteredClient
 * @see ServiceAccount
 * @see ServiceAccountRepository
 */
@Service
public class DefaultRegisteredClientRepository implements RegisteredClientRepository {

    @Autowired
    private ServiceAccountRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {
        // Copy RegisteredClient on Client
        ServiceAccount client = ServiceAccount.builder()
            .id(registeredClient.getId())
            .clientId(registeredClient.getClientId())
            .clientIdIssuedAt(registeredClient.getClientIdIssuedAt())
            .clientSecret(registeredClient.getClientSecret())
            .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt())
            .clientName(registeredClient.getClientName())
            .clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods())
            .authorizationGrantTypes(registeredClient.getAuthorizationGrantTypes())
            .redirectUris(registeredClient.getRedirectUris())
            .scopes(registeredClient.getScopes())
            .clientSettings(registeredClient.getClientSettings())
            .tokenSettings(registeredClient.getTokenSettings())
            .build();
        
        // Save client
        clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        System.out.println("findById");

        // Find Client
        Optional<ServiceAccount> clientHolder = clientRepository.findById(id);

        // If client does not exist
        if (clientHolder.isEmpty()) {

            // Return null
            return null;
        }

        // Get Client
        ServiceAccount client = clientHolder.get();
        
        // Return RegisteredClient
        return RegisteredClient
            .withId(client.getId())
            .clientId(client.getClientId())
            .clientIdIssuedAt(client.getClientIdIssuedAt())
            .clientSecret(client.getClientSecret())
            .clientSecretExpiresAt(client.getClientSecretExpiresAt())
            .clientName(client.getClientName())
            .clientAuthenticationMethods((methods) -> methods.addAll(client.getClientAuthenticationMethods()))
            .authorizationGrantTypes((grantTypes) -> grantTypes.addAll(client.getAuthorizationGrantTypes()))
            .redirectUris((redirectUris) -> redirectUris.addAll(client.getRedirectUris()))
            .scopes((scopes) -> scopes.addAll(client.getScopes()))
            .clientSettings(client.getClientSettings())
            .tokenSettings(client.getTokenSettings())
            .build();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        if (clientId.equals("dev")) {
            return RegisteredClient
                .withId("dev")
                .clientId(clientId)
                .clientIdIssuedAt(Instant.now())
                .clientSecret(passwordEncoder.encode("dev"))
                .clientSecretExpiresAt(null)
                .clientName("dev")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1/oauth/callback")
                .redirectUri("http://localhost/oauth/callback")
                .redirectUri("https://openidconnect.net/callback")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().build())
                .build();
        }

        // Find Client
        Optional<ServiceAccount> clientHolder = clientRepository.findByClientId(clientId);

        // If client does not exist
        if (clientHolder.isEmpty()) {

            // Return null
            return null;
        }

        // Get Client
        ServiceAccount client = clientHolder.get();
        
        // Return RegisteredClient
        return RegisteredClient
            .withId(client.getId())
            .clientId(client.getClientId())
            .clientIdIssuedAt(client.getClientIdIssuedAt())
            .clientSecret(client.getClientSecret())
            .clientSecretExpiresAt(client.getClientSecretExpiresAt())
            .clientName(client.getClientName())
            .clientAuthenticationMethods((methods) -> methods.addAll(client.getClientAuthenticationMethods()))
            .authorizationGrantTypes((grantTypes) -> grantTypes.addAll(client.getAuthorizationGrantTypes()))
            .redirectUris((redirectUris) -> redirectUris.addAll(client.getRedirectUris()))
            .scopes((scopes) -> scopes.addAll(client.getScopes()))
            .clientSettings(client.getClientSettings())
            .tokenSettings(client.getTokenSettings())
            .build();
    }
    
}
