package oidc.management.controller;

import oidc.management.model.AuthorizationInfo;
import oidc.management.model.Scope;
import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootTest(classes = {
        AuthorizationInfoController.class
})
public class AuthorizationInfoControllerTests {

    @Autowired
    private AuthorizationInfoController authorizationInfoController;

    @MockBean
    private RegisteredClientRepository registeredClientRepository;

    @MockBean
    private ServiceAccountService serviceAccountService;

    @Mock
    private Principal principal;

    @Mock
    private RegisteredClient registeredClient;

    @Mock
    private ServiceAccount serviceAccount;

    @Mock
    private Scope scope;

    @Test
    public void testGetAuthorizationInfo() {
        // Define variables
        final String clientId = UUID.randomUUID().toString();
        final String scopeName = "openid";
        final String state = UUID.randomUUID().toString();

        // Mock findByClientId
        Mockito.when(registeredClientRepository.findByClientId(clientId)).thenReturn(registeredClient);

        // Mock findByClientId
        Mockito.when(serviceAccountService.findByClientId(clientId)).thenReturn(Optional.of(serviceAccount));

        // Mock getScopes
        Mockito.when(serviceAccount.getScopes()).thenReturn(Set.of(scope));

        // Mock getName
        Mockito.when(scope.getName()).thenReturn(scopeName);

        // Test getAuthorizationInfo
        ResponseEntity<AuthorizationInfo> responseEntity = authorizationInfoController
                .getAuthorizationInfo(principal, clientId, scopeName, state);

        // Get body
        AuthorizationInfo authorizationInfo = responseEntity.getBody();

        // Validate body
        Assertions.assertNotNull(authorizationInfo);

        // Validate status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Validate scopes
        Assertions.assertEquals(1, authorizationInfo.getScopes().size());
        Assertions.assertEquals(scope, authorizationInfo.getScopes().stream().findFirst().get());
    }

    @Test
    public void testGetAuthorizationInfoInvalidRegisteredClient() {
        // Define variables
        final String clientId = UUID.randomUUID().toString();
        final String scopeName = "openid";
        final String state = UUID.randomUUID().toString();

        // Mock findByClientId
        Mockito.when(registeredClientRepository.findByClientId(clientId)).thenReturn(null);

        // Test getAuthorizationInfo
        ResponseEntity<AuthorizationInfo> responseEntity = authorizationInfoController
                .getAuthorizationInfo(principal, clientId, scopeName, state);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAuthorizationInfoInvalidServiceAccount() {
        // Define variables
        final String clientId = UUID.randomUUID().toString();
        final String scopeName = "openid";
        final String state = UUID.randomUUID().toString();

        // Mock findByClientId
        Mockito.when(registeredClientRepository.findByClientId(clientId)).thenReturn(registeredClient);

        // Mock findByClientId
        Mockito.when(serviceAccountService.findByClientId(clientId)).thenReturn(Optional.empty());

        // Test getAuthorizationInfo
        ResponseEntity<AuthorizationInfo> responseEntity = authorizationInfoController
                .getAuthorizationInfo(principal, clientId, scopeName, state);

        // Validate body
        Assertions.assertNull(responseEntity.getBody());

        // Validate status code
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
