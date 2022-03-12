package oidc.management.util;

import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import lombok.extern.java.Log;
import oidc.management.model.Authority;
import oidc.management.properties.DefaultAuthorityProperties;
import oidc.management.repository.AuthorityRepository;

/**
 * Creates default authorities.
 * 
 * @author Matias Hermosilla
 * @since 20-02-2022
 * @see DefaultUserAccountProperties
 */
@Log
@ConditionalOnProperty(prefix = "oidc.management.default.authority", name = "enabled", havingValue = "true")
@ConditionalOnBean(DefaultAuthorityProperties.class)
public class DefaultAuthoritySeeder {

    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Creates a default user account.
     */
    @PostConstruct
    public void seed() {

        // Find if the authority already exists
        Optional<Authority> optAuthority = authorityRepository.findByName("ROLE_OIDC_ADMIN");

        // If the authority does not exist, create it
        if (!optAuthority.isPresent()) {

            // Create the authority object
            Authority authority = Authority.builder()
                    .name("ROLE_OIDC_ADMIN")
                    .description("Administrator role for OIDC")
                    .build();

            // Save the authority
            this.authorityRepository.save(authority);

            // Log the creation of the authority
            log.info("Created default authority \"" + authority.getName() + "\"");

        }

    }
    
}
