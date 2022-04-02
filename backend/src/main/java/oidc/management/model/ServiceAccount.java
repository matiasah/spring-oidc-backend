package oidc.management.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

    @NotNull
    private String clientId;

    @CreatedDate
    private Instant clientIdIssuedAt;

    @NotNull
    @Getter(onMethod_ = {@JsonIgnore})
    @Setter(onMethod_ = {@JsonSetter})
    private String clientSecret;

    private Instant clientSecretExpiresAt;

    private String clientName;

    private String clientDescription;

    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    private Set<AuthorizationGrantType> authorizationGrantTypes;

    private Set<String> redirectUris;

    @DBRef
    private Set<Scope> scopes;

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
    private List<Authority> authorities;

    /**
     * The user's tags
     * DO NOT ENCRYPT THIS FIELD, IT'S USED FOR SEARCHING/FILTERING SERVICE ACCOUNTS.
     **/
    private Set<String> tags;

    @Transient
    public ClientSettings getClientSettings() {
        return ClientSettings.builder()
                .requireAuthorizationConsent(this.requireAuthenticationConsent)
                .requireProofKey(this.requireProofKey)
                .build();
    }

    @Transient
    public void setClientSettings(ClientSettings clientSettings) {
        this.setRequireAuthenticationConsent(clientSettings.isRequireAuthorizationConsent());
        this.setRequireProofKey(clientSettings.isRequireProofKey());
    }

    @Transient
    public TokenSettings getTokenSettings() {
        return TokenSettings.builder()
                .accessTokenTimeToLive(this.getAccessTokenTimeToLive())
                .idTokenSignatureAlgorithm(this.getIdTokenSignatureAlgorithm())
                .refreshTokenTimeToLive(this.getRefreshTokenTimeToLive())
                .reuseRefreshTokens(this.isReuseRefreshTokens())
                .build();
    }

    @Transient
    public void setTokenSettings(TokenSettings tokenSettings) {
        this.setAccessTokenTimeToLive(tokenSettings.getAccessTokenTimeToLive());
        this.setIdTokenSignatureAlgorithm(tokenSettings.getIdTokenSignatureAlgorithm());
        this.setRefreshTokenTimeToLive(tokenSettings.getRefreshTokenTimeToLive());
        this.setReuseRefreshTokens(tokenSettings.isReuseRefreshTokens());
    }

    /**
     * Service Account builder
     */
    public static class ServiceAccountBuilder {

        /**
         * Sets client settings
         *
         * @param clientSettings Client settings
         * @return Service Account Builder
         */
        public ServiceAccountBuilder clientSettings(ClientSettings clientSettings) {
            // Set client settings
            this.requireAuthenticationConsent(clientSettings.isRequireAuthorizationConsent());
            this.requireProofKey(clientSettings.isRequireProofKey());

            // Return the builder
            return this;
        }

        /**
         * Sets token settings
         *
         * @param tokenSettings Token settings
         * @return Service Account Builder
         */
        public ServiceAccountBuilder tokenSettings(TokenSettings tokenSettings) {
            // Set token settings
            this.accessTokenTimeToLive(tokenSettings.getAccessTokenTimeToLive());
            this.idTokenSignatureAlgorithm(tokenSettings.getIdTokenSignatureAlgorithm());
            this.refreshTokenTimeToLive(tokenSettings.getRefreshTokenTimeToLive());
            this.reuseRefreshTokens(tokenSettings.isReuseRefreshTokens());

            // Return the builder
            return this;
        }

    }

}
