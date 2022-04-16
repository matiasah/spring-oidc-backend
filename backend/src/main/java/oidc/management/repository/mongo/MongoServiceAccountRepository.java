package oidc.management.repository.mongo;

import oidc.management.model.ServiceAccount;
import oidc.management.model.mongo.MongoServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo implementation of the {@link ServiceAccountRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(MongoRepositoriesAutoConfiguration.class)
public interface MongoServiceAccountRepository extends ServiceAccountRepository<MongoServiceAccount>, MongoRepository<MongoServiceAccount, String> {

    @Override
    public default ServiceAccount.ServiceAccountBuilder entityBuilder() {
        return MongoServiceAccount.builder();
    }

}
