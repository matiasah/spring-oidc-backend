package oidc.management.jwk.strategy;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.proc.SecurityContext;
import oidc.management.jwk.JwkProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {
        ScheduledJWKSourceStrategy.class
})
public class ScheduledJWKSourceStrategyTests {

    @Autowired
    private ScheduledJWKSourceStrategy scheduledJWKSourceStrategy;

    @MockBean
    private JwkProvider jwkProvider;

    @Mock
    private JWK jwk;

    @Mock
    private JWKMatcher jwkMatcher;

    @Mock
    private SecurityContext securityContext;

    @Test
    public void testGet() {
        // Create JWKSelector
        final JWKSelector jwkSelector =  new JWKSelector(jwkMatcher);

        // Test get
        List<JWK> response = scheduledJWKSourceStrategy.get(jwkSelector, securityContext);

        // Validate response
        Assertions.assertEquals(0, response.size());
    }

    @Test
    public void testUpdateJwks() {
        // Create a list of JWK
        List<JWK> jwks = Arrays.asList(jwk);

        // Mock getJwks
        Mockito.when(jwkProvider.getJwks()).thenReturn(jwks);

        // Test updateJwks
        scheduledJWKSourceStrategy.updateJwks();
    }

    @Test
    public void testUpdateJwksAndGet() {
        // Create a list of JWK
        List<JWK> jwks = Arrays.asList(jwk);

        // Mock getJwks
        Mockito.when(jwkProvider.getJwks()).thenReturn(jwks);

        // Test updateJwks
        scheduledJWKSourceStrategy.updateJwks();

        // Create JWKSelector
        final JWKSelector jwkSelector =  new JWKSelector(jwkMatcher);

        // Test get
        List<JWK> response = scheduledJWKSourceStrategy.get(jwkSelector, securityContext);

        // Validate response size
        Assertions.assertEquals(1, response.size());

        // Validate response value
        Assertions.assertEquals(jwk, response.get(0));
    }

}
