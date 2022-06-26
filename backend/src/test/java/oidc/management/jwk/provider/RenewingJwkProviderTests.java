package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import oidc.management.jwk.RSAKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {
        RenewingJwkProvider.class,
        RSAKeyGenerator.class
})
public class RenewingJwkProviderTests {

    /**
     * Maximum number of JWKs to be generated.
     */
    @Value("${oidc.management.jwk.renewing.limit}")
    private int limit;

    @Autowired
    private RenewingJwkProvider renewingJwkProvider;

    @Test
    public void testGetJwks() {
        // Get JWKs
        List<JWK> jwks = renewingJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(1, jwks.size());
    }

    @Test
    public void testRenew() {
        // From 0 to limit
        for (int i = 0; i < limit; i++) {

            // Renew
            renewingJwkProvider.renew();
        }
    }

}
