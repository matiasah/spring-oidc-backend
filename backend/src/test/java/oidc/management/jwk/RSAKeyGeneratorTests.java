package oidc.management.jwk;

import com.nimbusds.jose.jwk.RSAKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@SpringBootTest(classes = {
        RSAKeyGenerator.class
})
public class RSAKeyGeneratorTests {

    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    @Test
    public void testGenerateRsa() {
        // Test generateRsa
        RSAKey rsaKey = rsaKeyGenerator.generateRsa();

        // Validate value
        Assertions.assertNotNull(rsaKey);
    }

    @Test
    public void testNoSuchAlgorithmException() {
        // Mock KeyPairGenerator
        try (MockedStatic<KeyPairGenerator> mock = Mockito.mockStatic(KeyPairGenerator.class)) {

            // Mock getInstance
            mock.when(() -> KeyPairGenerator.getInstance(Mockito.anyString())).thenThrow(new NoSuchAlgorithmException());

            // Test generateRsa
            Assertions.assertThrows(IllegalStateException.class, () -> rsaKeyGenerator.generateRsa());
        }
    }

}
