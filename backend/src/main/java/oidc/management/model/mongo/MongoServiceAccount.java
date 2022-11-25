package oidc.management.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import oidc.management.model.ServiceAccount;
import oidc.management.serialization.AuthorizationGrantTypeDeserializer;
import oidc.management.serialization.AuthorizationGrantTypeSerializer;
import oidc.management.serialization.ClientAuthenticationMethodDeserializer;
import oidc.management.serialization.ClientAuthenticationMethodSerializer;
import oidc.management.validation.annotations.ValidServiceAccount;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@Document(collection = "service_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ValidServiceAccount
public class MongoServiceAccount implements ServiceAccount<MongoScope, MongoAuthority> {

    /**
     * Application id.
     */
    @Id
    private String id;

    @NotNull
    private String clientId;

    /**
     * The hashed client id of the service account, for searching purposes.
     *
     * @see {@link oidc.management.service.ServiceAccountEncryptionService#getHashedClientId(String)}
     */
    @JsonIgnore
    private String hashedClientId;

    @CreatedDate
    private Instant clientIdIssuedAt;

    @NotNull
    @Getter(onMethod_ = {@JsonIgnore})
    @Setter(onMethod_ = {@JsonSetter})
    private String clientSecret;

    private Instant clientSecretExpiresAt;

    private String clientName;

    private String clientDescription;

    @JsonSerialize(using = ClientAuthenticationMethodSerializer.class)
    @JsonDeserialize(using = ClientAuthenticationMethodDeserializer.class)
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    @JsonSerialize(using = AuthorizationGrantTypeSerializer.class)
    @JsonDeserialize(using = AuthorizationGrantTypeDeserializer.class)
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    private Set<String> redirectUris;

    @DBRef
    private Set<MongoScope> scopes;

    /**
     * OIDC Client settings.
     */
    private boolean requireAuthenticationConsent = false;
    private boolean requireProofKey = false;

    /**
     * JWT Token settings
     */
    private Duration accessTokenTimeToLive;
    private SignatureAlgorithm idTokenSignatureAlgorithm;
    private Duration refreshTokenTimeToLive;
    private boolean reuseRefreshTokens = true;

    /**
     * Authorities
     */
    @DBRef
    private List<MongoAuthority> authorities;

    /**
     * The service account's tags
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING SERVICE ACCOUNTS.
     *
     * @see {@link oidc.management.repository.ServiceAccountRepository#findByTagsContainingIgnoreCase(String, Pageable)}
     **/
    private Set<String> tags;

    /**
     * Service Account builder
     */
    public static class MongoServiceAccountBuilder implements ServiceAccount.ServiceAccountBuilder<MongoScope, MongoAuthority> {

    }

}
