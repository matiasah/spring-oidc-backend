package oidc.management.repository.mongo;

import oidc.management.model.UserAccount;
import oidc.management.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo implementation of the {@link UserAccountRepository} interface.
 *
 * @author Matías Hermosilla
 * @since 14-04-2022
 */
@ConditionalOnBean(MongoRepositoriesAutoConfiguration.class)
public interface MongoUserAccountRepository extends UserAccountRepository, MongoRepository<UserAccount, String> {

}
