package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
@SpringBootTest(classes = {
        AuthorizationServerConfig.class
})
public class AuthorizationServerConfigTests {

    @MockBean
    private CorsConfigurationSource corsConfigurationSource;

    @MockBean
    private RegisteredClientRepository registeredClientRepository;

    @MockBean
    private JWKSource jwkSource;

    @Test
    public void testBeans() {

    }

}
