package oidc.management.service.impl;

import java.util.Optional;

import oidc.management.model.Authority;
import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import oidc.management.model.Scope;
import oidc.management.model.ServiceAccount;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link RegisteredClientRepository}.
 * 
 * @author Matías Hermosilla
 * @since 23-01-2022
 * @see RegisteredClientRepository
 * @see RegisteredClient
 * @see ServiceAccount
 */
@Service
public class DefaultRegisteredClientRepository implements RegisteredClientRepository {

    @Autowired
    private ServiceAccountService serviceAccountService;

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    @Override
    public RegisteredClient findById(String id) {
        // Find Client
        Optional<ServiceAccount> clientHolder = serviceAccountService.findById(id);

        // If client does not exist
        if (clientHolder.isEmpty()) {

            // Return null
            return null;
        }

        // Get Client
        ServiceAccount<? extends Scope, ? extends Authority> client = clientHolder.get();
        
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
            .scopes((scopes) -> scopes.addAll(client.getScopes().stream().map(Scope::getName).collect(java.util.stream.Collectors.toSet())))
            .clientSettings(client.getClientSettings())
            .tokenSettings(client.getTokenSettings())
            .build();
    }

    @Transactional
    @Override
    public RegisteredClient findByClientId(String clientId) {

        // Find Client
        Optional<ServiceAccount> clientHolder = serviceAccountService.findByClientId(clientId);

        // If client does not exist
        if (clientHolder.isEmpty()) {

            // Return null
            return null;
        }

        // Get Client
        ServiceAccount<? extends Scope, ? extends Authority> client = clientHolder.get();
        
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
            .scopes((scopes) -> scopes.addAll(client.getScopes().stream().map(Scope::getName).collect(java.util.stream.Collectors.toSet())))
            .clientSettings(client.getClientSettings())
            .tokenSettings(client.getTokenSettings())
            .build();
    }
    
}
