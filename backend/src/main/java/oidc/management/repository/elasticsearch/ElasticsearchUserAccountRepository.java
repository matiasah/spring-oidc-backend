package oidc.management.repository.elasticsearch;

import oidc.management.model.UserAccount;
import oidc.management.model.elasticsearch.ElasticsearchUserAccount;
import oidc.management.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@ConditionalOnBean(ElasticsearchRepositoriesAutoConfiguration.class)
public interface ElasticsearchUserAccountRepository extends UserAccountRepository<ElasticsearchUserAccount>, ElasticsearchRepository<ElasticsearchUserAccount, String> {

    @Override
    public default UserAccount.UserAccountBuilder entityBuilder() {
        return ElasticsearchUserAccount.builder();
    }

}
