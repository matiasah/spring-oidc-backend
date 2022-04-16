package oidc.management.repository.mongo;

import oidc.management.model.Authority;
import oidc.management.model.mongo.MongoAuthority;
import oidc.management.repository.AuthorityRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo implementation of the {@link AuthorityRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(MongoRepositoriesAutoConfiguration.class)
public interface MongoAuthorityRepository extends AuthorityRepository<MongoAuthority>, MongoRepository<MongoAuthority, String> {

    @Override
    public default Authority.AuthorityBuilder entityBuilder() {
        return MongoAuthority.builder();
    }

}
