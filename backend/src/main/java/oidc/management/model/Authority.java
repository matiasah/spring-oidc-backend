package oidc.management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import oidc.management.repository.AuthorityRepository;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Spring Security Authority model.
 * 
 * @author Matías Hermosilla
 * @since 02-02-2022
 * @see GrantedAuthority
 * @see AuthorityRepository
 */
@Builder
@Document(collection = "authorities")
@Data
@EqualsAndHashCode(of = "id")
public class Authority implements GrantedAuthority {

    @Id
    private String id;

    @NotNull
    private String name;

    private String description;
    
    @Override
    public String getAuthority() {
        return name;
    }

    /**
     * The scope's tags
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING AUTHORITIES.
     **/
    private Set<String> tags;
    
}
