package oidc.management.service.impl;

import java.util.List;
import java.util.Optional;

import oidc.management.model.Authority;
import oidc.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import oidc.management.model.UserAccount;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find user by username
        Optional<UserAccount> userHolder = userAccountService.findByUsername(username);

        // If user was not found
        if (userHolder.isEmpty()) {

            // Throw exception
            throw new UsernameNotFoundException("User not found");
        }

        // Get user
        UserAccount user = userHolder.get();

        // Fetch authorities
        List<Authority> authorities = user.getAuthorities();

        // If authorities is not null
        if (authorities != null) {

            // Fetch size (for JPA)
            authorities.size();
        }

        // Return user
        return user;
    }

    
}
