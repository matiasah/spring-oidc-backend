package oidc.management.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import oidc.management.model.Scope;
import oidc.management.validation.annotations.ValidScope;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Document(collection = "scopes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidScope
public class MongoScope implements Scope {

    @Id
    private String id;

    @NotNull
    private String name;

    /**
     * The hashed name of the scope, for searching purposes.
     *
     * @see {@link oidc.management.service.ScopeEncryptionService#getHashedName(String)}
     */
    @JsonIgnore
    private String hashedName;

    private String description;

    /**
     * The scope's tags
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING SCOPES.
     *
     * @see {@link oidc.management.repository.ScopeRepository#findByTagsContainingIgnoreCase(String, Pageable)}
     **/
    private Set<String> tags;

    public static class MongoScopeBuilder implements ScopeBuilder {

    }

}
