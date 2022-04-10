package oidc.management.repository;

import java.util.Optional;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import oidc.management.model.Authority;
import oidc.management.model.QAuthority;

/**
 * Spring Security Authority Repository.
 * 
 * @author Matías Hermosilla
 * @since 02-02-2022
 * @see MongoRepository
 * @see Authority
 * @see QAuthority
 * @see QuerydslPredicateExecutor
 * @see QuerydslBinderCustomizer
 * @see QuerydslBindings
 */
public interface AuthorityRepository extends MongoRepository<Authority, String>, QuerydslPredicateExecutor<Authority>, QuerydslBinderCustomizer<QAuthority> {

    /**
     * Finds an authority by its name.
     * 
     * @param authorityName The name of the authority to find.
     * @return The authority if it exists, otherwise an empty optional.
     */
    public Optional<Authority> findByName(String authorityName);
    
    @Override
    public default void customize(QuerydslBindings bindings, QAuthority qModel) {
        bindings.bind(qModel.id).first((path, value) -> path.eq(value));
        bindings.bind(Long.class).first((NumberPath<Long> path, Long value) -> path.eq(value));
        bindings.bind(Integer.class).first((NumberPath<Integer> path, Integer value) -> path.eq(value));
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    public Page<Authority> findAllByNameContaining(String search, Pageable pageable);
}
