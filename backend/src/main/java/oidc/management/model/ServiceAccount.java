package oidc.management.model;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OIDC Service Account model.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 */
@Document(collection = "service_accounts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class ServiceAccount {
    
    /**
     * Application id.
     */
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;

    @Getter(onMethod_ = {@JsonIgnore})
    @Setter(onMethod_ = {@JsonSetter})
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private ClientSettings clientSettings;
    private TokenSettings tokenSettings;
    private List<Authority> authorities;

}
