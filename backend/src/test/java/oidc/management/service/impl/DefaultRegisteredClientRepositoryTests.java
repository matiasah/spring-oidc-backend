package oidc.management.service.impl;

import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountService;
import oidc.management.test.TestServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootTest(classes = {
        DefaultRegisteredClientRepository.class
})
public class DefaultRegisteredClientRepositoryTests {

    @Autowired
    private DefaultRegisteredClientRepository defaultRegisteredClientRepository;

    @MockBean
    private ServiceAccountService serviceAccountService;

    @Mock
    private RegisteredClient registeredClient;

    @Test
    public void testSave() {
        // Test save
        Assertions.assertThrows(UnsupportedOperationException.class, () -> defaultRegisteredClientRepository.save(registeredClient));
    }

    @Test
    public void testFindById() {

        // Generate ID
        final String id = UUID.randomUUID().toString();

        // Mock ServiceAccount
        ServiceAccount serviceAccount = TestServiceAccount.builder()
                .id(id)
                .clientId(UUID.randomUUID().toString())
                .clientIdIssuedAt(Instant.now())
                .clientSecret(UUID.randomUUID().toString())
                .clientName("test")
                .clientAuthenticationMethods(Set.of(ClientAuthenticationMethod.NONE))
                .authorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE))
                .redirectUris(Set.of("http://localhost"))
                .scopes(Collections.emptySet())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofSeconds(1))
                        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
                        .refreshTokenTimeToLive(Duration.ofSeconds(1))
                        .build())
                .build();

        // Mock findById
        Mockito.when(serviceAccountService.findById(id)).thenReturn(Optional.of(serviceAccount));

        // Test findById
        RegisteredClient response = defaultRegisteredClientRepository.findById(id);

        // Validate response
        Assertions.assertNotNull(response);

    }

    @Test
    public void testFindByIdNotFound() {

        // Generate ID
        final String id = UUID.randomUUID().toString();

        // Mock findById
        Mockito.when(serviceAccountService.findById(id)).thenReturn(Optional.empty());

        // Test findById
        RegisteredClient response = defaultRegisteredClientRepository.findById(id);

        // Validate response
        Assertions.assertNull(response);

    }

    @Test
    public void testFindByClientId() {

        // Generate ID
        final String clientId = UUID.randomUUID().toString();

        // Mock ServiceAccount
        ServiceAccount serviceAccount = TestServiceAccount.builder()
                .id(UUID.randomUUID().toString())
                .clientId(clientId)
                .clientIdIssuedAt(Instant.now())
                .clientSecret(UUID.randomUUID().toString())
                .clientName("test")
                .clientAuthenticationMethods(Set.of(ClientAuthenticationMethod.NONE))
                .authorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE))
                .redirectUris(Set.of("http://localhost"))
                .scopes(Collections.emptySet())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofSeconds(1))
                        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
                        .refreshTokenTimeToLive(Duration.ofSeconds(1))
                        .build())
                .build();

        // Mock findById
        Mockito.when(serviceAccountService.findByClientId(clientId)).thenReturn(Optional.of(serviceAccount));

        // Test findById
        RegisteredClient response = defaultRegisteredClientRepository.findByClientId(clientId);

        // Validate response
        Assertions.assertNotNull(response);

    }

    @Test
    public void testFindByClientIdNotFound() {

        // Generate ID
        final String clientId = UUID.randomUUID().toString();

        // Mock findById
        Mockito.when(serviceAccountService.findByClientId(clientId)).thenReturn(Optional.empty());

        // Test findById
        RegisteredClient response = defaultRegisteredClientRepository.findByClientId(clientId);

        // Validate response
        Assertions.assertNull(response);

    }

}
