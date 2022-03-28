package oidc.management.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import oidc.management.model.UserAccount;
import oidc.management.repository.UserAccountRepository;

/**
 * Default implementation of {@link UserDetailsService}
 * 
 * @author Matías Hermosilla
 * @since 23-01-2022
 * @see UserDetailsService
 * @see UserAccount
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    /**
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Temporal user
        if (username.equals("admin")) {

            // Return user
            return User.builder()
                .username(username)
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
        }

        // Find user by username
        Optional<UserAccount> userHolder = userAccountRepository.findByUsername(username);

        // If user was not found
        if (userHolder.isEmpty()) {

            // Throw exception
            throw new UsernameNotFoundException("User not found");
        }

        // Return user
        return userHolder.get();
    }

    
}
