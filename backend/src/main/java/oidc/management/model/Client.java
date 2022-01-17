package oidc.management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * OIDC Client model.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 */
@Document(collection = "clients")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = true)
public class Client extends RegisteredClient {
    
    /**
     * Application id.
     */
    @Id
    private String id;

}
