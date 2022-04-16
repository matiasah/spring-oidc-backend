package oidc.management.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import oidc.management.model.Authority;
import oidc.management.model.jpa.JpaAuthority;

import java.io.IOException;

public class AuthorityDeserializer extends JsonDeserializer<Authority> {

    @Override
    public Authority deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return null;
    }

}
