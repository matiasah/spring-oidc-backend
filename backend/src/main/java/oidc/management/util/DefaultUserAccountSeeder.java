package oidc.management.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import oidc.management.service.AuthorityService;
import oidc.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.java.Log;
import oidc.management.model.Authority;
import oidc.management.model.UserAccount;
import oidc.management.properties.DefaultUserAccountProperties;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creates a default user account.
 * 
 * @author Matias Hermosilla
 * @since 20-02-2022
 * @see DefaultUserAccountProperties
 */
@Log
@Component("defaultUserAccountSeeder")
@DependsOn("defaultAuthoritySeeder")
@ConditionalOnProperty(prefix = "oidc.management.default.user-account", name = "enabled", havingValue = "true")
@ConditionalOnBean({DefaultUserAccountProperties.class, DefaultAuthoritySeeder.class})
public class DefaultUserAccountSeeder {

    @Autowired
    private DefaultUserAccountProperties defaultUserAccountProperties;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a default user account.
     */
    @Transactional
    @EventListener(ContextStartedEvent.class)
    @Order(4)
    public void seed() {

        // Get the default authority
        Optional<Authority> optAuthority = this.authorityService.findByName("ROLE_OIDC_ADMIN");

        // If the authority exists
        if (optAuthority.isPresent()) {

            // Get the name of the user account
            String username = defaultUserAccountProperties.getUsername();

            // Find if the user account already exists
            Optional<UserAccount> optUserAccount = userAccountService.findByUsername(username);

            // If the user account does not exist, create it
            if (!optUserAccount.isPresent()) {

                // Create the user account object
                UserAccount userAccount = userAccountService.entityBuilder()
                        .username(username)
                        .password(passwordEncoder.encode(defaultUserAccountProperties.getPassword()))
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .authorities(Arrays.asList(optAuthority.get()))
                        .tags(new HashSet<>(Arrays.asList("default")))
                        .build();

                // Save the user account
                this.userAccountService.save(userAccount);

                // Log
                log.info("Created default user account with name \"" + defaultUserAccountProperties.getUsername() + "\" and password \"" + defaultUserAccountProperties.getPassword() + "\"");

            } else {

                // Log
                log.info("The default user account \"" + defaultUserAccountProperties.getUsername() + "\" already exists");

            }

        } else {
                
            // Warn
            log.warning("The default authority \"ROLE_OIDC_ADMIN\" does not exist");

        }

    }
    
}
