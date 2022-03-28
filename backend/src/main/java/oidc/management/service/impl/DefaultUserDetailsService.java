package oidc.management.service.impl;

import java.util.Optional;

import oidc.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import oidc.management.model.UserAccount;

/**
 * Default implementation of {@link UserAccountService}
 * 
 * @author Matías Hermosilla
 * @since 23-01-2022
 * @see UserAccountService
 * @see UserAccount
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    /**
     * @see UserAccountService#findByUsername(String)
     */
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find user by username
        Optional<UserAccount> userHolder = userAccountService.findByUsername(username);

        // If user was not found
        if (userHolder.isEmpty()) {

            // Throw exception
            throw new UsernameNotFoundException("User not found");
        }

        // Return user
        return userHolder.get();
    }

    
}
