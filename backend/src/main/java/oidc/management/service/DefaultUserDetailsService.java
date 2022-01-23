package oidc.management.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import oidc.management.model.User;
import oidc.management.repository.UserRepository;

/**
 * Default implementation of {@link UserDetailsService}
 * 
 * @author Matías Hermosilla
 * @since 23-01-2022
 * @see UserDetailsService
 * @see User
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    /**
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user by username
        Optional<User> userHolder = userRepository.findByUsername(username);

        // If user was not found
        if (userHolder.isEmpty()) {

            // Throw exception
            throw new UsernameNotFoundException("User not found");
        }

        // Return user
        return userHolder.get();
    }

    
}
