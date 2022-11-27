package oidc.management.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;

@ExtendWith(MockitoExtension.class)
public class ServiceAccountTests {

    @Spy
    private ServiceAccount serviceAccount;

    @Spy
    private ServiceAccount.ServiceAccountBuilder serviceAccountBuilder;

    @Mock
    private ClientSettings clientSettings;

    @Mock
    private TokenSettings tokenSettings;

    @Test
    public void testGetClientSettings() {
        // Test getClientSettings
        Assertions.assertNotNull(serviceAccount.getClientSettings());
    }

    @Test
    public void testSetClientSettings() {
        // Test setClientSettings
        serviceAccount.setClientSettings(clientSettings);
    }

    @Test
    public void testGetTokenSettings() {
        // Mock getAccessTokenTimeToLive
        Mockito.when(serviceAccount.getAccessTokenTimeToLive()).thenReturn(Duration.ofSeconds(1));

        // Mock getIdTokenSignatureAlgorithm
        Mockito.when(serviceAccount.getIdTokenSignatureAlgorithm()).thenReturn(SignatureAlgorithm.RS256);

        // Mock refreshTokenTimeToLive
        Mockito.when(serviceAccount.getRefreshTokenTimeToLive()).thenReturn(Duration.ofSeconds(1));

        // Test getTokenSettings
        Assertions.assertNotNull(serviceAccount.getTokenSettings());
    }

    @Test
    public void testSetTokenSettings() {
        // Test setTokenSettings
        serviceAccount.setTokenSettings(tokenSettings);
    }

    @Test
    public void testBuilderClientSettings() {
        // Test clientSettings
        serviceAccountBuilder.clientSettings(clientSettings);
    }

    @Test
    public void testBuilderTokenSettings() {
        // Test tokenSettings
        serviceAccountBuilder.tokenSettings(tokenSettings);
    }

}
