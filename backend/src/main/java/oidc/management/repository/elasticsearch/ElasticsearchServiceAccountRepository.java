package oidc.management.repository.elasticsearch;

import oidc.management.model.ServiceAccount;
import oidc.management.model.elasticsearch.ElasticsearchServiceAccount;
import oidc.management.repository.ServiceAccountRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@ConditionalOnBean(ElasticsearchRepositoriesAutoConfiguration.class)
public interface ElasticsearchServiceAccountRepository extends ServiceAccountRepository<ElasticsearchServiceAccount>, ElasticsearchRepository<ElasticsearchServiceAccount, String> {

    @Override
    public default ServiceAccount.ServiceAccountBuilder entityBuilder() {
        return ElasticsearchServiceAccount.builder();
    }

}
