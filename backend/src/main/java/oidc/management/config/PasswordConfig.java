package oidc.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration for the password encoder.
 *
 * @author Matias Hermosilla
 * @since 19-03-2022
 */
@Configuration
public class PasswordConfig {

    /**
     * Argon2 password encoder bean.
     *
     * @return Argon2 password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
