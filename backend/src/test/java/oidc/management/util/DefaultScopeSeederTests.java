package oidc.management.util;

import oidc.management.properties.DefaultScopeProperties;
import oidc.management.service.ScopeService;
import oidc.management.service.impl.DefaultScopeEncryptionService;
import oidc.management.service.impl.DefaultScopeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

@EnableConfigurationProperties
@Import({
        DefaultScopeProperties.class,
        DefaultScopeSeeder.class,
        DefaultScopeService.class,
        DefaultScopeEncryptionService.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class DefaultScopeSeederTests {

    private static final Set<String> DEFAULT_SCOPES_TAGS = Set.of("default");

    @Autowired
    private DefaultScopeSeeder defaultScopeSeeder;

    @Autowired
    private ScopeService scopeService;

    @Test
    public void testSeed() {

        // Test seed
        defaultScopeSeeder.seed();

    }

    @Test
    public void testSeedWithExistingScopes() {

        // Seed scopes
        defaultScopeSeeder.seed();

        // Test seed
        defaultScopeSeeder.seed();

    }

}
