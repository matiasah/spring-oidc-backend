package oidc.management.repository;

import java.util.Optional;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import oidc.management.model.QScope;
import oidc.management.model.Scope;

public interface ScopeRepository extends MongoRepository<Scope, String>, QuerydslPredicateExecutor<Scope>, QuerydslBinderCustomizer<QScope> {

    /**
     * Finds a scope by its name.
     * 
     * @param scopeName The name of the scope to find.
     * @return The scope if it exists, otherwise an empty optional.
     */
    public Optional<Scope> findByName(String scopeName);
    
    @Override
    public default void customize(QuerydslBindings bindings, QScope qModel) {
        bindings.bind(qModel.id).first((path, value) -> path.eq(value));
        bindings.bind(Long.class).first((NumberPath<Long> path, Long value) -> path.eq(value));
        bindings.bind(Integer.class).first((NumberPath<Integer> path, Integer value) -> path.eq(value));
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

}
