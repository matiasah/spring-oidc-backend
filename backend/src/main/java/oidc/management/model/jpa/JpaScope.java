package oidc.management.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import oidc.management.model.Scope;
import oidc.management.validation.annotations.ValidScope;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidScope
public class JpaScope implements Scope {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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
    @ElementCollection
    private Set<String> tags;

    public static class JpaScopeBuilder implements ScopeBuilder {

    }

}
