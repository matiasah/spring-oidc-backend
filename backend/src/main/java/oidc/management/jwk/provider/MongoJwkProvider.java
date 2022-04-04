package oidc.management.jwk.provider;

import com.mongodb.client.FindIterable;
import com.nimbusds.jose.jwk.JWK;
import lombok.extern.log4j.Log4j2;
import oidc.management.jwk.JwkProvider;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * JWK provider that pulls JWKs from a MongoDB collection.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
@Log4j2
public class MongoJwkProvider implements JwkProvider {

    /**
     * Name of the collection from which to pull JWKs.
     */
    @Value("${oidc.management.jwk.mongo.collection}")
    private String collection;

    /**
     * MongoTemplate for accessing the list of JWKs.
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<JWK> getJwks() {
        // Initialize a list of JWKs
        List<JWK> jwks = new ArrayList<>();

        // Find all documents in the collection
        FindIterable<Document> iterable = mongoTemplate.getCollection(collection).find(Document.class);

        // For every document
        for (Document document : iterable) {

            try {
                // Parse the map into a JWK
                JWK jwk = JWK.parse(document);

                // Add the JWK to the list
                jwks.add(jwk);
            } catch (Exception e) {

                // Log the document that could not be parsed
                log.error("Could not parse document {}", document.getObjectId("_id").toString(), e);
            }

        }

        // Return the list of JWKs
        return jwks;
    }

}
