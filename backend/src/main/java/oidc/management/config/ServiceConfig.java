package oidc.management.config;

import oidc.management.service.*;
import oidc.management.service.impl.*;
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
     * Default implementation of {@link ServiceAccountEncryptionService}.
     *
     * @return An instance of {@link DefaultServiceAccountEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceAccountEncryptionService serviceAccountEncryptionService() {
        return new DefaultServiceAccountEncryptionService();
    }

    /**
     * Default implementation of {@link ServiceAccountService}.
     *
     * @return An instance of {@link DefaultServiceAccountService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceAccountService serviceAccountService() {
        return new DefaultServiceAccountService();
    }

    /**
     * Default implementation of {@link UserAccountEncryptionService}.
     *
     * @return An instance of {@link DefaultUserAccountEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public UserAccountEncryptionService userAccountEncryptionService() {
        return new DefaultUserAccountEncryptionService();
    }

    /**
     * Default implementation of {@link UserAccountService}.
     *
     * @return An instance of {@link DefaultUserAccountService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public UserAccountService userAccountService() {
        return new DefaultUserAccountService();
    }

    /**
     * Default implementation of {@link ScopeEncryptionService}.
     *
     * @return An instance of {@link DefaultScopeEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public ScopeEncryptionService scopeEncryptionService() {
        return new DefaultScopeEncryptionService();
    }

    /**
     * Default implementation of {@link ScopeService}.
     *
     * @return An instance of {@link DefaultScopeService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public ScopeService scopeService() {
        return new DefaultScopeService();
    }

    /**
     * Default implementation of {@link AuthorityEncryptionService}.
     *
     * @return An instance of {@link DefaultAuthorityEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthorityEncryptionService authorityEncryptionService() {
        return new DefaultAuthorityEncryptionService();
    }

    /**
     * Default implementation of {@link AuthorityService}.
     *
     * @return An instance of {@link DefaultAuthorityService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthorityService authorityService() {
        return new DefaultAuthorityService();
    }

    /**
     * Default implementation of {@link AuditEventEncryptionService}.
     *
     * @return An instance of {@link DefaultAuditEventEncryptionService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public AuditEventEncryptionService auditEventEncryptionService() {
        return new DefaultAuditEventEncryptionService();
    }

    /**
     * Default implementation of {@link AuditEventService}.
     *
     * @return An instance of {@link DefaultAuditEventService}.
     */
    @Bean
    @ConditionalOnMissingBean
    public AuditEventService auditEventService() {
        return new DefaultAuditEventService();
    }

}
