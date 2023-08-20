package oidc.management.jwk.provider;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.nimbusds.jose.jwk.JWK;
import oidc.management.jwk.RSAKeyGenerator;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {
        MongoJwkProvider.class,
        RSAKeyGenerator.class
})
public class MongoJwkProviderTests {

    @Value("${oidc.management.jwk.mongo.collection}")
    private String collection;

    @Autowired
    private MongoJwkProvider mongoJwkProvider;

    @Autowired
    private RSAKeyGenerator rsaKeyGenerator;

    @MockBean
    private MongoOperations mongoOperations;

    @Mock
    private MongoCollection<Document> mongoCollection;

    @Mock
    private FindIterable<Document> findIterable;

    @Mock
    private MongoCursor<Document> mongoCursor;

    @Test
    public void testGetJwks() {

        // Generate JWK
        Map<String, Object> key = rsaKeyGenerator.generateRsa().toJSONObject();

        // Convert JWK to Document
        Document documentKey = new Document(key);

        // Create a List of document
        List<Document> documents = List.of(documentKey);

        // Mock getCollection()
        Mockito.when(mongoOperations.getCollection(collection)).thenReturn(mongoCollection);

        // Mock find()
        Mockito.when(mongoCollection.find(Document.class)).thenReturn(findIterable);

        // Real Iterator
        Iterator<Document> documentIterator = documents.iterator();

        // Mock hasNext()
        Mockito.when(mongoCursor.hasNext()).thenAnswer((invocation) -> documentIterator.hasNext());

        // Mock next()
        Mockito.when(mongoCursor.next()).thenAnswer((invocation) -> documentIterator.next());

        // Mock iterator()
        Mockito.when(findIterable.iterator()).thenReturn(mongoCursor);

        // Get Jwks
        List<JWK> jwks = mongoJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(1, jwks.size());

    }

    @Test
    public void testGetJwksWithInvalidData() {

        // Generate JWK
        Map<String, Object> key = Map.of("_id", new ObjectId());

        // Convert JWK to Document
        Document documentKey = new Document(key);

        // Create a List of document
        List<Document> documents = List.of(documentKey);

        // Mock getCollection()
        Mockito.when(mongoOperations.getCollection(collection)).thenReturn(mongoCollection);

        // Mock find()
        Mockito.when(mongoCollection.find(Document.class)).thenReturn(findIterable);

        // Real Iterator
        Iterator<Document> documentIterator = documents.iterator();

        // Mock hasNext()
        Mockito.when(mongoCursor.hasNext()).thenAnswer((invocation) -> documentIterator.hasNext());

        // Mock next()
        Mockito.when(mongoCursor.next()).thenAnswer((invocation) -> documentIterator.next());

        // Mock iterator()
        Mockito.when(findIterable.iterator()).thenReturn(mongoCursor);

        // Get Jwks
        List<JWK> jwks = mongoJwkProvider.getJwks();

        // Validate JWKs
        Assertions.assertEquals(0, jwks.size());

    }

}
