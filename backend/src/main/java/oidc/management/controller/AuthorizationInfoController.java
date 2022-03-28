package oidc.management.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import oidc.management.model.Scope;
import oidc.management.model.ServiceAccount;
import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import oidc.management.model.AuthorizationInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

/**
 * Authorization info controller.
 * 
 * @author Matías Hermosilla
 * @since 29-01-2022
 */
@RestController
public class AuthorizationInfoController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Autowired
    private ServiceAccountService serviceAccountService;

    /**
     * Get client info.
     * 
     * @param principal The principal.
     * @param clientId The client id.
     * @return The client info.
     */
    @GetMapping("oauth2/authorization-info")
    public ResponseEntity<AuthorizationInfo> getAuthorizationInfo(
            final @AuthenticationPrincipal Principal principal,
            final @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
            final @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
            final @RequestParam(OAuth2ParameterNames.STATE) String state
        ) {

        // Find client by id
        final RegisteredClient client = registeredClientRepository.findByClientId(clientId);

        // Check if client exists
        if (client == null) {

            // Registered client not found
            return ResponseEntity.notFound().build();
        }

        // Find Service Account (RegisteredClient) by id
        final Optional<ServiceAccount> optServiceAccount = serviceAccountService.findByClientId(clientId);

        // Check if Service Account exists
        if (!optServiceAccount.isPresent()) {

            // Service Account not found
            return ResponseEntity.notFound().build();
        }

        // Get requested scopes
        final Set<String> requestedScopes = Arrays
                .stream(StringUtils.delimitedListToStringArray(scope, " "))
                .collect(Collectors.toSet());

        // Get Service Account
        final ServiceAccount serviceAccount = optServiceAccount.get();

        // Get scopes
        final Set<Scope> scopes = serviceAccount
                .getScopes()
                .stream()
                .filter(scopeObject -> requestedScopes.contains(scopeObject.getName()))
                .collect(Collectors.toSet());

        // Authorization info
        final AuthorizationInfo authorizationInfo = new AuthorizationInfo();

        authorizationInfo.setPrincipal(principal);
        authorizationInfo.setClient(client);
        authorizationInfo.setScopes(scopes);
        authorizationInfo.setState(state);

        // Return authorization info
        return ResponseEntity.ok(authorizationInfo);
    }

}
