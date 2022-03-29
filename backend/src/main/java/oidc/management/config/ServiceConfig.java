package oidc.management.config;

import oidc.management.service.ServiceAccountEncryptionService;
import oidc.management.service.ServiceAccountService;
import oidc.management.service.UserAccountEncryptionService;
import oidc.management.service.UserAccountService;
import oidc.management.service.impl.DefaultServiceAccountEncryptionService;
import oidc.management.service.impl.DefaultServiceAccountService;
import oidc.management.service.impl.DefaultUserAccountEncryptionService;
import oidc.management.service.impl.DefaultUserAccountService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for creating default implementations of services when custom implementations are not provided.
 *
 * @author Matías Hermosilla
 * @since 28-03-2022
 */
@Configuration
public class ServiceConfig {

    /**
     * Default implementation of {@link ServiceAccountEncryptionService}
     *
     * @return An instance of {@link DefaultServiceAccountEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceAccountEncryptionService serviceAccountEncryptionService() {
        return new DefaultServiceAccountEncryptionService();
    }

    /**
     * Default implementation of {@link ServiceAccountService}
     *
     * @return An instance of {@link DefaultServiceAccountService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceAccountService serviceAccountService() {
        return new DefaultServiceAccountService();
    }

    /**
     * Default implementation of {@link UserAccountEncryptionService}
     *
     * @return An instance of {@link DefaultUserAccountEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public UserAccountEncryptionService userAccountEncryptionService() {
        return new DefaultUserAccountEncryptionService();
    }

    /**
     * Default implementation of {@link UserAccountService}
     *
     * @return An instance of {@link DefaultUserAccountService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public UserAccountService userAccountService() {
        return new DefaultUserAccountService();
    }

}
