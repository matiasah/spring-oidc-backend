package oidc.management.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {
        JwtConfig.class
})
public class JwtConfigTests {

    @MockBean
    private JWKSource jwkSource;

    @Test
    public void testContext() {

    }

}
