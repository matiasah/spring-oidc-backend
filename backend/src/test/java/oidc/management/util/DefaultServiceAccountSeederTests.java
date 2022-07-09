package oidc.management.util;

import oidc.management.properties.DefaultScopeProperties;
import oidc.management.properties.DefaultServiceAccountProperties;
import oidc.management.service.impl.DefaultScopeEncryptionService;
import oidc.management.service.impl.DefaultScopeService;
import oidc.management.service.impl.DefaultServiceAccountEncryptionService;
import oidc.management.service.impl.DefaultServiceAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

@EnableConfigurationProperties
@Import({
        DefaultServiceAccountProperties.class,
        DefaultScopeProperties.class,
        DefaultScopeSeeder.class,
        DefaultServiceAccountSeeder.class,
        DefaultServiceAccountService.class,
        DefaultServiceAccountEncryptionService.class,
        DefaultScopeService.class,
        DefaultScopeEncryptionService.class,
        NoOpPasswordEncoder.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class DefaultServiceAccountSeederTests {

    @Autowired
    private DefaultScopeSeeder defaultScopeSeeder;

    @Autowired
    private DefaultServiceAccountSeeder defaultServiceAccountSeeder;

    @Autowired
    private DefaultServiceAccountProperties defaultServiceAccountProperties;

    @Test
    public void testSeed() {

        // Seed scopes
        defaultScopeSeeder.seed();

        // Test seed
        defaultServiceAccountSeeder.seed();

    }

    @Test
    public void testSeedWithoutRedirectUri() {

        // Empty redirectUris
        defaultServiceAccountProperties.getRedirectUris().clear();

        // Test seed
        defaultServiceAccountSeeder.seed();

    }

    @Test
    public void testSeedWithoutScopes() {

        // Test seed
        defaultServiceAccountSeeder.seed();

    }

    @Test
    public void testSeedWithExistingServiceAccount () {

        // Seed scopes
        defaultScopeSeeder.seed();

        // Seed Service Account
        defaultServiceAccountSeeder.seed();

        // Test seed
        defaultServiceAccountSeeder.seed();

    }

}
