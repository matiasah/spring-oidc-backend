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
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = {
        ObjectMapper.class
})
public class ClientAuthenticationMethodDeserializerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Spy
    private TestServiceAccount serviceAccount;

    @Test
    public void testDeserialize() throws JsonProcessingException {
        // Create list of ClientAuthenticationMethod
        Set<ClientAuthenticationMethod> clientAuthenticationMethods = Set.of(
                ClientAuthenticationMethod.CLIENT_SECRET_BASIC,
                ClientAuthenticationMethod.CLIENT_SECRET_POST,
                ClientAuthenticationMethod.CLIENT_SECRET_JWT,
                ClientAuthenticationMethod.PRIVATE_KEY_JWT,
                ClientAuthenticationMethod.NONE
        );

        // Mock getClientAuthenticationMethods
        Mockito.when(serviceAccount.getClientAuthenticationMethods()).thenReturn(clientAuthenticationMethods);

        // Serialize service account
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Deserialize service account
        final TestServiceAccount outputServiceAccount = objectMapper.readValue(valueAsString, TestServiceAccount.class);

        // Validate service account
        Assertions.assertNotNull(outputServiceAccount);
    }

    @Test
    public void testDeserializeInvalid() throws JsonProcessingException {
        // Create list of ClientAuthenticationMethod
        Set<ClientAuthenticationMethod> clientAuthenticationMethods = Set.of(new ClientAuthenticationMethod("abc"));

        // Mock getClientAuthenticationMethods
        Mockito.when(serviceAccount.getClientAuthenticationMethods()).thenReturn(clientAuthenticationMethods);

        // Serialize service account
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Deserialize service account
        Assertions.assertThrows(InvalidFormatException.class, () -> objectMapper.readValue(valueAsString, TestServiceAccount.class));
    }

    @Test
    public void testDeserializeNull() throws JsonProcessingException {
        // Create list of ClientAuthenticationMethod
        Set<ClientAuthenticationMethod> clientAuthenticationMethods = new HashSet<>();

        // Put null value
        clientAuthenticationMethods.add(null);

        // Mock getAuthorizationGrantTypes
        Mockito.when(serviceAccount.getClientAuthenticationMethods()).thenReturn(clientAuthenticationMethods);

        // Serialize service account
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Deserialize service account
        Assertions.assertThrows(InvalidFormatException.class, () -> objectMapper.readValue(valueAsString, TestServiceAccount.class));
    }

    @Test
    public void testDeserializeInvalidData() throws JsonProcessingException {
        // Deserialize service account
        Assertions.assertThrows(InvalidFormatException.class, () -> objectMapper.readValue("{\"clientAuthenticationMethods\": \"invalid\"}", TestServiceAccount.class));
    }

}
