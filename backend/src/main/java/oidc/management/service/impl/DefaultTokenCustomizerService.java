package oidc.management.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import oidc.management.service.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Service;
import oidc.management.model.Authority;
import oidc.management.model.ServiceAccount;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link OAuth2TokenCustomizer} that adds the
 * {@link ServiceAccount}'s {@link Authority}s to the {@link Authentication}'s
 * {@link Collection} of {@link GrantedAuthority}s.
 * 
 * @author Matías Hermosilla
 * @since 03-02-2022
 * @see OAuth2TokenCustomizer
 * @see ServiceAccount
 * @see Authority
 * @see Authentication
 */
@Service
public class DefaultTokenCustomizerService implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private static final String AUTHORITIES_CLAIM = "authorities";

    @Autowired
    private ServiceAccountService serviceAccountService;

    @Transactional
    @Override
    public void customize(JwtEncodingContext jwtEncodingContext) {

        // Set to contain authorities
        Set<String> authorities = new HashSet<>();

        // Get authentication
        Authentication auth = jwtEncodingContext.getPrincipal();

        // If authentication is not null
        if (auth != null) {

            // Get authorities
            Collection<? extends GrantedAuthority> authenticationAuthorities = auth.getAuthorities();

            // If authentication authorities is not null
            if (authenticationAuthorities != null) {

                // For each authority
                for (GrantedAuthority authority : authenticationAuthorities) {

                    // Add authority to authorities
                    authorities.add(authority.getAuthority());
                }

            }

        }

        // Get RegisteredClient
        RegisteredClient registeredClient = jwtEncodingContext.getRegisteredClient();

        // Get ServiceAccount
        Optional<ServiceAccount> optServiceAccount = serviceAccountService.findById(registeredClient.getId());

        // If the ServiceAccount exists
        if (optServiceAccount.isPresent()) {

            // Get ServiceAccount
            ServiceAccount serviceAccount = optServiceAccount.get();

            // Get authorities from ServiceAccount
            List<Authority> serviceAccountAuthorities = serviceAccount.getAuthorities();

            // If serviceAccountAuthorities is not null
            if (serviceAccountAuthorities != null) {

                // For every ServiceAccount authority
                for (Authority authority : serviceAccountAuthorities) {
                    
                    // Add to authorities
                    authorities.add(authority.getAuthority());

                }

            }

        }

        // Add authorities
        jwtEncodingContext.getClaims().claim(AUTHORITIES_CLAIM, authorities);

    }
    
}
