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
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = {
        ObjectMapper.class
})
public class ClientAuthenticationMethodSerializerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Spy
    private TestServiceAccount serviceAccount;

    @Test
    public void testSerialize() throws JsonProcessingException {
        // Create list of ClientAuthenticationMethod
        Set<ClientAuthenticationMethod> clientAuthenticationMethods = Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);

        // Mock getClientAuthenticationMethods
        Mockito.when(serviceAccount.getClientAuthenticationMethods()).thenReturn(clientAuthenticationMethods);

        // Serialize serviceAccount
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Validate value
        Assertions.assertNotNull(valueAsString);
        Assertions.assertNotEquals("", valueAsString);
    }

    @Test
    public void testSerializeNull() throws JsonProcessingException {
        // Create list of ClientAuthenticationMethod
        Set<ClientAuthenticationMethod> clientAuthenticationMethods = new HashSet<>();

        // Put null
        clientAuthenticationMethods.add(null);

        // Mock getClientAuthenticationMethods
        Mockito.when(serviceAccount.getClientAuthenticationMethods()).thenReturn(clientAuthenticationMethods);

        // Serialize serviceAccount
        final String valueAsString = objectMapper.writeValueAsString(serviceAccount);

        // Validate value
        Assertions.assertNotNull(valueAsString);
        Assertions.assertNotEquals("", valueAsString);
    }

}
