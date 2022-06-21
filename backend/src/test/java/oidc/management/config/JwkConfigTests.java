package oidc.management.config;

import oidc.management.jwk.JwkProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwkConfigTests {

    @InjectMocks
    private JwkConfig jwkConfig;

    @Mock
    private JwkProvider jwkProvider;

    @Test
    public void testSimpleJwkSourceStrategy() {
        // Test simpleJwkSourceStrategy
        Assertions.assertNotNull(jwkConfig.simpleJwkSourceStrategy(jwkProvider));
    }

    @Test
    public void testScheduledJwkSourceStrategy() {
        // Test scheduledJwkSourceStrategy
        Assertions.assertNotNull(jwkConfig.scheduledJwkSourceStrategy(jwkProvider));
    }

    @Test
    public void testCachingJwkSourceStrategy() {
        // Test cachingJwkSourceStrategy
        Assertions.assertNotNull(jwkConfig.cachingJwkSourceStrategy(jwkProvider));
    }

    @Test
    public void testSimpleJwkProvider() {
        // Test simpleJwkProvider
        Assertions.assertNotNull(jwkConfig.simpleJwkProvider());
    }

    @Test
    public void testRenewingJwkProvider() {
        // Test renewingJwkProvider
        Assertions.assertNotNull(jwkConfig.renewingJwkProvider());
    }

    @Test
    public void testVaultJwkProvider() {
        // Test vaultJwkProvider
        Assertions.assertNotNull(jwkConfig.vaultJwkProvider());
    }

    @Test
    public void testMongoJwkProvider() {
        // Test mongoJwkProvider
        Assertions.assertNotNull(jwkConfig.mongoJwkProvider());
    }

}
