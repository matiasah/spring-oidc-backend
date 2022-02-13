package oidc.management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Spring Security Scope model.
 * 
 * @author Matías Hermosilla
 * @since 13-02-2022
 */
@Builder
@Document(collection = "scopes")
@Data
@EqualsAndHashCode(of = "id")
public class Scope {
    
    @Id
    private String id;

    private String name;

    private String description;

}
