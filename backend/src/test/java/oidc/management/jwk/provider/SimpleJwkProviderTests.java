package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import oidc.management.jwk.RSAKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {
        SimpleJwkProvider.class,
        RSAKeyGenerator.class
})
public class SimpleJwkProviderTests {

    @Autowired
    private SimpleJwkProvider simpleJwkProvider;

    @Test
    public void testGetJwks() {
        // Get JWKs
        List<JWK> jwks = simpleJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(1, jwks.size());
    }

}
