package oidc.management.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oidc.management.test.TestServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Set;

@SpringBootTest(classes = {
        ObjectMapper.class
})
public class AuthorizationGrantTypeSerializerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Spy
    private TestServiceAccount serviceAccount;

    @Test
    public void testSerialize() throws JsonProcessingException {
        // Create list of Authorization
        Set<AuthorizationGrantType> authorizationGrantTypes = Set.of(AuthorizationGrantType.AUTHORIZATION_CODE);

        // Mock getAuthorizationGrantTypes
        Mockito.when(serviceAccount.getAuthorizationGrantTypes()).thenReturn(authorizationGrantTypes);

        // Serialize serviceAccount
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Validate value
        Assertions.assertNotNull(valueAsString);
        Assertions.assertNotEquals("", valueAsString);
    }

}
