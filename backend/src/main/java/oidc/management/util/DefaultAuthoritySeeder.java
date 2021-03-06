package oidc.management.util;

import java.util.Optional;
import java.util.Set;

import oidc.management.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import lombok.extern.java.Log;
import oidc.management.model.Authority;
import oidc.management.properties.DefaultAuthorityProperties;
import org.springframework.transaction.annotation.Transactional;

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

    public static final String ROLE_OIDC_ADMIN = "ROLE_OIDC_ADMIN";

    @Autowired
    private AuthorityService authorityService;

    /**
     * Creates a default user account.
     */
    @Transactional
    @EventListener(ContextStartedEvent.class)
    @Order(1)
    public void seed() {

        // Find if the authority already exists
        Optional<Authority> optAuthority = authorityService.findByName(ROLE_OIDC_ADMIN);

        // If the authority exists
        if (optAuthority.isPresent()) {

            // Info
            log.info("The default authority \"" + ROLE_OIDC_ADMIN + "\" already exists");

            // Stop execution
            return;

        }

        // Create the authority object
        Authority authority = authorityService.entityBuilder()
                .name(ROLE_OIDC_ADMIN)
                .description("Administrator role for OIDC")
                .tags(Set.of("default"))
                .build();

        // Save the authority
        this.authorityService.save(authority);

        // Log the creation of the authority
        log.info("Created default authority \"" + authority.getName() + "\"");

    }
    
}
