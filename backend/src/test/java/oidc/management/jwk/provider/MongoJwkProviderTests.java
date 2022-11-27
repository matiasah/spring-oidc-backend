package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import oidc.management.jwk.RSAKeyGenerator;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Map;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import({
        MongoJwkProvider.class,
        RSAKeyGenerator.class
})
@DataMongoTest
public class MongoJwkProviderTests {

    @Value("${oidc.management.jwk.mongo.collection}")
    private String collection;

    @Autowired
    private MongoJwkProvider mongoJwkProvider;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    @BeforeEach
    public void cleanUp() {

        // Clean collection
        mongoTemplate.getCollection(collection).drop();

    }

    @Test
    public void testGetJwks() {

        // Generate JWK
        Map<String, Object> key = rsaKeyGenerator.generateRsa().toJSONObject();

        // Save test JWK
        mongoTemplate.getCollection(collection).insertOne(new Document(key));

        // Get Jwks
        List<JWK> jwks = mongoJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(1, jwks.size());

    }

    @Test
    public void testGetJwksWithInvalidData() {

        // Save test JWK
        mongoTemplate.getCollection(collection).insertOne(new Document(Map.of("ABC", "123")));

        // Get Jwks
        List<JWK> jwks = mongoJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(0, jwks.size());

    }

}
