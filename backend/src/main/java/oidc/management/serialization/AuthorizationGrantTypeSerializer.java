package oidc.management.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Set;

/**
 * Serializes a {@link Set} of {@link AuthorizationGrantType}s to a JSON string.
 * 
 * @author Matías Hermosilla
 * @since 02-04-2022
 */
public class AuthorizationGrantTypeSerializer extends StdSerializer<Set<AuthorizationGrantType>> {

    public AuthorizationGrantTypeSerializer() {
        super(new ObjectMapper().getTypeFactory().constructCollectionType(Set.class, AuthorizationGrantType.class));
    }

    @Override
    public void serialize(Set<AuthorizationGrantType> value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider provider) throws java.io.IOException {
        gen.writeStartArray();
        for (AuthorizationGrantType grantType : value) {
            if (grantType != null) {
                gen.writeString(grantType.getValue());
            } else {
                gen.writeNull();
            }
        }
        gen.writeEndArray();
    }

}
