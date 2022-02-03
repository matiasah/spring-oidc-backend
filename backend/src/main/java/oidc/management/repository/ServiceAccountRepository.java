package oidc.management.repository;

import java.util.Optional;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import oidc.management.model.ServiceAccount;
import oidc.management.model.QServiceAccount;

/**
 * OIDC Service Account Repository.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see ServiceAccount
 */
public interface ServiceAccountRepository extends MongoRepository<ServiceAccount, String>, QuerydslPredicateExecutor<ServiceAccount>, QuerydslBinderCustomizer<QServiceAccount> {

    /**
     * Finds a client by client id.
     */
    public Optional<ServiceAccount> findByClientId(String clientId);

    @Override
    public default void customize(QuerydslBindings bindings, QServiceAccount qModel) {
        bindings.bind(Long.class).first((NumberPath<Long> path, Long value) -> path.eq(value));
        bindings.bind(Integer.class).first((NumberPath<Integer> path, Integer value) -> path.eq(value));
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(qModel.clientSecret);
    }
    
}
