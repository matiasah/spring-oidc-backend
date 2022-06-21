package oidc.management.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfigurationSource;

@SpringBootTest(classes = {
        DefaultSecurityConfig.class
})
public class DefaultSecurityConfigTests {

    @MockBean
    private CorsConfigurationSource corsConfigurationSource;

    @MockBean
    private JwtAuthenticationConverter jwtAuthenticationConverter;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    public void testContext() {

    }

}
