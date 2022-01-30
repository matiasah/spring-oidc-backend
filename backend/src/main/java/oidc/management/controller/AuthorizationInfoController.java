package oidc.management.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
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
 */
@RestController
public class AuthorizationInfoController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    /**
     * Get client info.
     * 
     * @param principal The principal.
     * @param clientId The client id.
     * @param scopes The scopes.
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
            return ResponseEntity.notFound().build();
        }

        // Get scopes
        final Set<String> scopes = Arrays.stream(StringUtils.delimitedListToStringArray(scope, " "))
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
