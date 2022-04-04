package oidc.management.jwk.provider;

import com.nimbusds.jose.jwk.JWK;
import oidc.management.jwk.JwkProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * JWK provider that pulls JWKs from a MongoDB collection.
 *
 * @author Matías Hermosilla
 * @since 03-04-2022
 */
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
        return mongoTemplate.findAll(JWK.class, collection);
    }

}
