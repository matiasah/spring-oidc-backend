package oidc.management.model;

import java.security.Principal;
import java.util.Set;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import lombok.Data;

@Data
public class AuthorizationInfo {

    private Principal principal;
    private RegisteredClient client;
    private Set<Scope> scopes;
    private String state;
    
}
