package oidc.management.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Deserializes a {@link Set} of {@link ClientAuthenticationMethod}s from a JSON array.
 *
 * @author Matías Hermosilla
 * @since 02-04-2022
 */
public class ClientAuthenticationMethodDeserializer extends StdDeserializer<Set<ClientAuthenticationMethod>> {

    public ClientAuthenticationMethodDeserializer() {
        super(new ObjectMapper().getTypeFactory().constructCollectionType(Set.class, ClientAuthenticationMethod.class));
    }

    @Override
    public Set<ClientAuthenticationMethod> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        // Parse the content and get the iterable
        JsonNode node = jp.getCodec().readTree(jp);

        // Check if the content is an array
        if (node.isArray()) {

            // Create a set to store the values
            Set<ClientAuthenticationMethod> methods = new HashSet<>(node.size());

            // Iterate over the array
            for (JsonNode element : node) {

                // Check if the element is a string
                if (element.isTextual()) {

                    // Get the value
                    String value = element.asText();

                    // Check if the value is null
                    if (value == null) {

                        // Throw an exception
                        throw new InvalidFormatException(jp, "The value of the json object cannot be null", null, ClientAuthenticationMethod.class);
                    }

                    // Check if the value is equal to the value of the client authentication method
                    if (value.equals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue())) {

                        // Add the client authentication method to the set
                        methods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
                    } else if (value.equals(ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue())) {

                        // Add the client authentication method to the set
                        methods.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
                    } else if (value.equals(ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue())) {

                        // Add the client authentication method to the set
                        methods.add(ClientAuthenticationMethod.CLIENT_SECRET_JWT);
                    } else if (value.equals(ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue())) {

                        // Add the client authentication method to the set
                        methods.add(ClientAuthenticationMethod.PRIVATE_KEY_JWT);
                    } else if (value.equals(ClientAuthenticationMethod.NONE.getValue())) {

                        // Add the client authentication method to the set
                        methods.add(ClientAuthenticationMethod.NONE);
                    } else {

                        // Throw an exception
                        throw new InvalidFormatException(jp, "The value of the json object is not valid", null, ClientAuthenticationMethod.class);
                    }

                } else {

                    // Throw an exception
                    throw new InvalidFormatException(jp, "The value of the json object is not valid", null, ClientAuthenticationMethod.class);
                }

            }

            // Return the set
            return methods;
        }

        // Throw an exception
        throw new InvalidFormatException(jp, "The json object is not an array", null, ClientAuthenticationMethod.class);
    }

}
