package oidc.management.service.impl;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Service;
import oidc.management.model.Authority;
import oidc.management.model.Scope;
import oidc.management.model.ServiceAccount;
import oidc.management.repository.ServiceAccountRepository;

/**
 * ServiceAccount Service for managing Service Accounts.
 * 
 * @author Matías Hermosilla
 * @since 03-02-2022
 * @see ServiceAccount
 * @see ServiceAccountRepository
 */
@Service
public class ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     * 
     * @param id The {@link String} identifier.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findById(String id) {

        // Find Service Account by id
        return serviceAccountRepository.findById(id);
    }

    /**
     * Finds a {@link ServiceAccount} by its {@link String} clientId.
     * 
     * @param clientId The {@link String} clientId.
     * @return An {@link Optional} containing the {@link ServiceAccount} if it
     *         exists, or an empty {@link Optional} if it does not.
     */
    public Optional<ServiceAccount> findByClientId(String clientId) {

        // Find Service Account by clientId
        return serviceAccountRepository.findByClientId(clientId);
    }
    
}
