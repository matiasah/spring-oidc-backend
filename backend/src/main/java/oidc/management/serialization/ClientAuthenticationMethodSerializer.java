package oidc.management.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;

/**
 * Serializes a {@link Set} of {@link ClientAuthenticationMethod}s to a JSON string.
 *
 * @author Matías Hermosilla
 * @since 02-04-2022
 */
public class ClientAuthenticationMethodSerializer extends StdSerializer<Set<ClientAuthenticationMethod>> {

    public ClientAuthenticationMethodSerializer() {
        super(new ObjectMapper().getTypeFactory().constructCollectionType(Set.class, ClientAuthenticationMethod.class));
    }

    @Override
    public void serialize(Set<ClientAuthenticationMethod> value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider provider) throws java.io.IOException {
        gen.writeStartArray();
        for (ClientAuthenticationMethod method : value) {
            if (method != null) {
                gen.writeString(method.getValue());
            } else {
                gen.writeNull();
            }
        }
        gen.writeEndArray();
    }

}
