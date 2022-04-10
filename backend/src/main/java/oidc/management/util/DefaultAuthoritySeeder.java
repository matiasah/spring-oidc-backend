package oidc.management.util;

import java.util.Optional;
import javax.annotation.PostConstruct;

import oidc.management.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import lombok.extern.java.Log;
import oidc.management.model.Authority;
import oidc.management.properties.DefaultAuthorityProperties;

/**
 * Creates default authorities.
 * 
 * @author Matias Hermosilla
 * @since 11-03-2022
 * @see DefaultAuthorityProperties
 */
@Log
@Component("defaultAuthoritySeeder")
@ConditionalOnProperty(prefix = "oidc.management.default.authority", name = "enabled", havingValue = "true")
@ConditionalOnBean(DefaultAuthorityProperties.class)
public class DefaultAuthoritySeeder {

    @Autowired
    private AuthorityService authorityService;

    /**
     * Creates a default user account.
     */
    @PostConstruct
    public void seed() {

        // Find if the authority already exists
        Optional<Authority> optAuthority = authorityService.findByName("ROLE_OIDC_ADMIN");

        // If the authority exists
        if (optAuthority.isPresent()) {

            // Info
            log.info("The default authority \"ROLE_OIDC_ADMIN\" already exists");

            // Stop execution
            return;

        }

        // Create the authority object
        Authority authority = Authority.builder()
                .name("ROLE_OIDC_ADMIN")
                .description("Administrator role for OIDC")
                .build();

        // Save the authority
        this.authorityService.save(authority);

        // Log the creation of the authority
        log.info("Created default authority \"" + authority.getName() + "\"");

    }
    
}
