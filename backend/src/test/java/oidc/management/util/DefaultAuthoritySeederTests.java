package oidc.management.util;

import oidc.management.service.AuthorityService;
import oidc.management.test.TestAuthority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DefaultAuthoritySeederTests {

    public static final String ROLE_OIDC_ADMIN = "ROLE_OIDC_ADMIN";

    @InjectMocks
    private DefaultAuthoritySeeder defaultAuthoritySeeder;

    @Mock
    private AuthorityService authorityService;

    @Test
    public void testSeed() {

        // Mock findByName
        Mockito.when(authorityService.findByName(ROLE_OIDC_ADMIN)).thenReturn(Optional.empty());

        // Mock entityBuilder
        Mockito.when(authorityService.entityBuilder()).thenReturn(TestAuthority.builder());

        // Test seed
        defaultAuthoritySeeder.seed();

    }

    @Test
    public void testSeedExists() {

        // Create TestAuthority
        TestAuthority testAuthority = TestAuthority.builder()
                .name(ROLE_OIDC_ADMIN)
                .description("test")
                .build();

        // Mock findByName
        Mockito.when(authorityService.findByName(ROLE_OIDC_ADMIN)).thenReturn(Optional.of(testAuthority));

        // Test seed
        defaultAuthoritySeeder.seed();

    }

}
