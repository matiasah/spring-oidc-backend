package oidc.management.repository;

import java.util.Optional;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import oidc.management.model.UserAccount;
import oidc.management.model.QUserAccount;

/**
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see MongoRepository
 * @see UserAccount
 * @see UserAccountRepository
 * @see UserAccountRepository#findByUsername(String)
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String>, QuerydslPredicateExecutor<UserAccount>, QuerydslBinderCustomizer<QUserAccount> {

    public Optional<UserAccount> findByUsername(String username);

    @Override
    public default void customize(QuerydslBindings bindings, QUserAccount qModel) {
        bindings.bind(Long.class).first((NumberPath<Long> path, Long value) -> path.eq(value));
        bindings.bind(Integer.class).first((NumberPath<Integer> path, Integer value) -> path.eq(value));
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(QUserAccount.userAccount.password);
    }
    
}
