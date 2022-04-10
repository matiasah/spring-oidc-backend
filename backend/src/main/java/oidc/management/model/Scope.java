package oidc.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Spring Security Scope model.
 * 
 * @author Matías Hermosilla
 * @since 13-02-2022
 */
@Builder
@Document(collection = "scopes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Scope {
    
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

}
