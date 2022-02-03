package oidc.management.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;
import oidc.management.model.Authority;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;

@Service
public class DefaultTokenCustomizerService implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private static final String AUTHORITIES_CLAIM = "authorities";

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Override
    public void customize(JwtEncodingContext jwtEncodingContext) {
        // Get RegisteredClient
        RegisteredClient registeredClient = jwtEncodingContext.getRegisteredClient();

        // Get ServiceAccount
        Optional<ServiceAccount> optServiceAccount = serviceAccountRepository.findById(registeredClient.getId());

        // If the ServiceAccount exists
        if (optServiceAccount.isPresent()) {
            // Get ServiceAccount
            ServiceAccount serviceAccount = optServiceAccount.get();

            // Set to contain authorities
            Set<String> authorities = new HashSet<>();

            // Get current authorities
            jwtEncodingContext.getClaims().claim(AUTHORITIES_CLAIM, authorities);

            // Get authorities from ServiceAccount
            List<Authority> serviceAccountAuthorities = serviceAccount.getAuthorities();

            // For every ServiceAccount authority
            for (Authority authority : serviceAccountAuthorities) {
                
                // Add to authorities
                authorities.add(authority.getAuthority());
            }
        }
    }
    
}
