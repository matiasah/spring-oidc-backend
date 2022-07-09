package oidc.management.util;

import oidc.management.properties.DefaultAuthorityProperties;
import oidc.management.service.AuthorityService;
import oidc.management.service.impl.DefaultAuthorityEncryptionService;
import oidc.management.service.impl.DefaultAuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

@EnableConfigurationProperties
@Import({
        DefaultAuthorityProperties.class,
        DefaultAuthoritySeeder.class,
        DefaultAuthorityService.class,
        DefaultAuthorityEncryptionService.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class DefaultAuthoritySeederTests {

    @Autowired
    private DefaultAuthoritySeeder defaultAuthoritySeeder;

    @Autowired
    private AuthorityService authorityService;

    @Test
    public void testSeed() {

        // Test seed
        defaultAuthoritySeeder.seed();

    }

    @Test
    public void testSeedWithExistingAuthorities() {

        // Seed authorities
        defaultAuthoritySeeder.seed();

        // Test seed
        defaultAuthoritySeeder.seed();

    }

}
