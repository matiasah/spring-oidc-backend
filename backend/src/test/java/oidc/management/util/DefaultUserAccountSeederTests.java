package oidc.management.util;

import oidc.management.properties.DefaultAuthorityProperties;
import oidc.management.properties.DefaultUserAccountProperties;
import oidc.management.service.impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

@EnableConfigurationProperties
@Import({
        DefaultUserAccountProperties.class,
        DefaultAuthorityProperties.class,
        DefaultAuthoritySeeder.class,
        DefaultUserAccountSeeder.class,
        DefaultUserAccountService.class,
        DefaultUserAccountEncryptionService.class,
        DefaultAuthorityService.class,
        DefaultAuthorityEncryptionService.class,
        NoOpPasswordEncoder.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class DefaultUserAccountSeederTests {

    @Autowired
    private DefaultAuthoritySeeder defaultAuthoritySeeder;

    @Autowired
    private DefaultUserAccountSeeder defaultUserAccountSeeder;

    @Test
    public void testSeed() {

        // Seed authorities
        defaultAuthoritySeeder.seed();

        // Test seed
        defaultUserAccountSeeder.seed();

    }

    @Test
    public void testSeedWithoutAuthorities() {

        // Test seed
        defaultUserAccountSeeder.seed();

    }

    @Test
    public void testSeedWithExistingUserAccount() {

        // Seed authorities
        defaultAuthoritySeeder.seed();

        // Seed user account
        defaultUserAccountSeeder.seed();

        // Test seed
        defaultUserAccountSeeder.seed();

    }

}
