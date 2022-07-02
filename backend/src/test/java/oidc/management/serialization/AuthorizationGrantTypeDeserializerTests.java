package oidc.management.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import oidc.management.test.TestServiceAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = {
        ObjectMapper.class
})
public class AuthorizationGrantTypeDeserializerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Spy
    private TestServiceAccount serviceAccount;

    @Test
    public void testDeserialize() throws JsonProcessingException {
        // Create list of Authorization
        Set<AuthorizationGrantType> authorizationGrantTypes = Set.of(
                AuthorizationGrantType.AUTHORIZATION_CODE,
                AuthorizationGrantType.REFRESH_TOKEN,
                AuthorizationGrantType.CLIENT_CREDENTIALS,
                AuthorizationGrantType.PASSWORD,
                AuthorizationGrantType.JWT_BEARER
        );

        // Mock getAuthorizationGrantTypes
        Mockito.when(serviceAccount.getAuthorizationGrantTypes()).thenReturn(authorizationGrantTypes);

        // Serialize service account
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Deserialize service account
        final TestServiceAccount outputServiceAccount = objectMapper.readValue(valueAsString, TestServiceAccount.class);

        // Validate service account
        Assertions.assertNotNull(outputServiceAccount);
    }

    @Test
    public void testDeserializeInvalid() throws JsonProcessingException {
        // Create list of Authorization
        Set<AuthorizationGrantType> authorizationGrantTypes = Set.of(new AuthorizationGrantType("abc"));

        // Mock getAuthorizationGrantTypes
        Mockito.when(serviceAccount.getAuthorizationGrantTypes()).thenReturn(authorizationGrantTypes);

        // Serialize service account
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Deserialize service account
        Assertions.assertThrows(InvalidFormatException.class, () -> objectMapper.readValue(valueAsString, TestServiceAccount.class));
    }

    @Test
    public void testDeserializeNull() throws JsonProcessingException {
        // Create list of Authorization
        Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();

        // Put null value
        authorizationGrantTypes.add(null);

        // Mock getAuthorizationGrantTypes
        Mockito.when(serviceAccount.getAuthorizationGrantTypes()).thenReturn(authorizationGrantTypes);

        // Serialize service account
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Deserialize service account
        Assertions.assertThrows(InvalidFormatException.class, () -> objectMapper.readValue(valueAsString, TestServiceAccount.class));
    }

    @Test
    public void testDeserializeInvalidData() throws JsonProcessingException {
        // Deserialize service account
        Assertions.assertThrows(InvalidFormatException.class, () -> objectMapper.readValue("{\"authorizationGrantTypes\": \"invalid\"}", TestServiceAccount.class));
    }


}
