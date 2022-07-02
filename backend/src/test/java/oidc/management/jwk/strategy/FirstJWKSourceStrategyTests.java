package oidc.management.jwk.strategy;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FirstJWKSourceStrategyTests {

    @InjectMocks
    private FirstJWKSourceStrategy firstJWKSourceStrategy;

    @Mock
    private JWKSource<SecurityContext> jwkSource;

    @Mock
    private JWK jwk;

    @Mock
    private JWKMatcher jwkMatcher;

    @Mock
    private SecurityContext securityContext;

    @Test
    public void testGet() throws KeySourceException {
        // Create a list of JWK
        List<JWK> jwks = Arrays.asList(jwk);

        // Create JWKSelector
        final JWKSelector jwkSelector =  new JWKSelector(jwkMatcher);

        // Mock get
        Mockito.when(jwkSource.get(jwkSelector, securityContext)).thenReturn(jwks);

        // Test get
        List<JWK> response = firstJWKSourceStrategy.get(jwkSelector, securityContext);

        // Validate response size
        Assertions.assertEquals(1, response.size());

        // Validate response value
        Assertions.assertEquals(jwk, response.get(0));
    }

    @Test
    public void testGetEmptyList() throws KeySourceException {
        // Create a list of JWK
        List<JWK> jwks = Collections.emptyList();

        // Create JWKSelector
        final JWKSelector jwkSelector =  new JWKSelector(jwkMatcher);

        // Mock get
        Mockito.when(jwkSource.get(jwkSelector, securityContext)).thenReturn(jwks);

        // Test get
        List<JWK> response = firstJWKSourceStrategy.get(jwkSelector, securityContext);

        // Validate response size
        Assertions.assertEquals(0, response.size());
    }

}
