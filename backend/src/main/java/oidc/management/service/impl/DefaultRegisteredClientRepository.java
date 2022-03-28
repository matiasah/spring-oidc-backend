package oidc.management.service.impl;

import java.util.Optional;

import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import oidc.management.model.Scope;
import oidc.management.model.ServiceAccount;

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
        /**
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
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
            .scopes((scopes) -> scopes.addAll(client.getScopes().stream().map(Scope::getName).collect(java.util.stream.Collectors.toSet())))
            .clientSettings(client.getClientSettings())
            .tokenSettings(client.getTokenSettings())
            .build();
    }

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
            .scopes((scopes) -> scopes.addAll(client.getScopes().stream().map(Scope::getName).collect(java.util.stream.Collectors.toSet())))
            .clientSettings(client.getClientSettings())
            .tokenSettings(client.getTokenSettings())
            .build();
    }
    
}
