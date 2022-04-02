package oidc.management.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Deserializes a {@link Set} of {@link AuthorizationGrantType}s from a JSON array.
 *
 * @author Matías Hermosilla
 * @since 02-04-2022
 */
public class AuthorizationGrantTypeDeserializer extends StdDeserializer<Set<AuthorizationGrantType>> {

    public AuthorizationGrantTypeDeserializer() {
        super(new ObjectMapper().getTypeFactory().constructCollectionType(Set.class, AuthorizationGrantType.class));
    }

    @Override
    public Set<AuthorizationGrantType> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        // Parse the content and get the iterable
        JsonNode node = jp.getCodec().readTree(jp);

        // Check if the node is an array
        if (node.isArray()) {

            // Create a set to store the values
            Set<AuthorizationGrantType> grantTypes = new HashSet<>(node.size());

            // Iterate over the array
            for (JsonNode element : node) {

                // Check if the element is a string
                if (element.isTextual()) {

                    // Get the value
                    String value = element.asText();

                    // Check if the value is null
                    if (value == null) {

                        // Throw an exception
                        throw new InvalidFormatException(jp, "The value of the json object cannot be null", null, AuthorizationGrantType.class);
                    }

                    // Check if the value is "authorization_code"
                    if (value.equals("authorization_code")) {

                        // Add the value to the set
                        grantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    } else if (value.equals("refresh_token")) {

                        // Add the value to the set
                        grantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
                    } else if (value.equals("client_credentials")) {

                        // Add the value to the set
                        grantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS);

                    } else if (value.equals("password")) {

                        // Add the value to the set
                        grantTypes.add(AuthorizationGrantType.PASSWORD);

                    } else if (value.equals("urn:ietf:params:oauth:grant-type:jwt-bearer")) {

                        // Add the value to the set
                        grantTypes.add(AuthorizationGrantType.JWT_BEARER);
                    } else {

                        // Throw an exception
                        throw new InvalidFormatException(jp, "The value of the json object is not a valid authorization grant type", null, AuthorizationGrantType.class);
                    }

                } else {

                    // Throw an exception
                    throw new InvalidFormatException(jp, "The value of the json object is not a string", null, AuthorizationGrantType.class);
                }

            }

            // Return the set
            return grantTypes;
        }

        // Throw an exception
        throw new InvalidFormatException(jp, "The value of the json object is not an array", null, AuthorizationGrantType.class);
    }

}
