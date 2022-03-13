package oidc.management.util;

import java.util.Arrays;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.java.Log;
import oidc.management.model.Authority;
import oidc.management.model.UserAccount;
import oidc.management.properties.DefaultUserAccountProperties;
import oidc.management.repository.AuthorityRepository;
import oidc.management.repository.UserAccountRepository;

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
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a default user account.
     */
    @PostConstruct
    public void seed() {

        // Get the default authority
        Optional<Authority> optAuthority = this.authorityRepository.findByName("ROLE_OIDC_ADMIN");

        // If the authority exists
        if (optAuthority.isPresent()) {

            // Get the name of the user account
            String username = defaultUserAccountProperties.getUsername();

            // Find if the user account already exists
            Optional<UserAccount> optUserAccount = userAccountRepository.findByUsername(username);

            // If the user account does not exist, create it
            if (!optUserAccount.isPresent()) {

                // Create the user account object
                UserAccount userAccount = UserAccount.builder()
                        .username(username)
                        .password(passwordEncoder.encode(defaultUserAccountProperties.getPassword()))
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .authorities(Arrays.asList(optAuthority.get()))
                        .build();

                // Save the user account
                this.userAccountRepository.save(userAccount);

                // Log
                log.info("Created a user service account with name \"" + defaultUserAccountProperties.getUsername() + "\" and password \"" + defaultUserAccountProperties.getPassword() + "\"");

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
