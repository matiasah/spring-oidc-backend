package oidc.management.jwk.provider;

import com.mongodb.client.MongoClients;
import com.nimbusds.jose.jwk.JWK;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import oidc.management.jwk.RSAKeyGenerator;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
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

    private MongodExecutable mongodExecutable;

    private MongoTemplate mongoTemplate;

    @Value("${oidc.management.jwk.mongo.collection}")
    private String collection;

    @Autowired
    private MongoJwkProvider mongoJwkProvider;

    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    @BeforeEach
    public void setup() throws Exception {

        final String CONNECTION_STRING = "mongodb://%s:%d";
        final String ip = "localhost";
        final int port = 27017;

        ImmutableMongodConfig mongodConfig = MongodConfig
            .builder()
            .version(Version.Main.V6_0)
            .net(new Net(ip, port, Network.localhostIsIPv6()))
            .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");

        // Clean collection
        mongoTemplate.getCollection(collection).drop();

    }

    @AfterEach
    public void clean() {

        // Stop MongoDB instance
        mongodExecutable.stop();

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
