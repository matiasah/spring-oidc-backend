package oidc.management.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import oidc.management.serialization.AuthorizationGrantTypeDeserializer;
import oidc.management.serialization.AuthorizationGrantTypeSerializer;
import oidc.management.serialization.ClientAuthenticationMethodDeserializer;
import oidc.management.serialization.ClientAuthenticationMethodSerializer;
import org.springframework.data.annotation.Transient;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import jakarta.validation.constraints.NotNull;

/**
 * OIDC Service Account model.
 * 
 * @author Matías Hermosilla
 * @since 16-01-2022
 * @see Scope
 * @see Authority
 */
public interface ServiceAccount<S extends Scope, A extends Authority> {

    public String getId();

    public void setId(String id);

    public String getClientId();

    public void setClientId(@NotNull String clientId);

    public String getHashedClientId();

    public void setHashedClientId(String hashedClientId);

    public Instant getClientIdIssuedAt();

    public void setClientIdIssuedAt(Instant clientIdIssuedAt);

    public String getClientSecret();

    public void setClientSecret(@NotNull String clientSecret);

    public Instant getClientSecretExpiresAt();

    public void setClientSecretExpiresAt(Instant clientSecretExpiresAt);

    public String getClientName();

    public void setClientName(String clientName);

    public String getClientDescription();

    public void setClientDescription(String clientDescription);

    @JsonSerialize(using = ClientAuthenticationMethodSerializer.class)
    public Set<ClientAuthenticationMethod> getClientAuthenticationMethods();

    @JsonDeserialize(using = ClientAuthenticationMethodDeserializer.class)
    public void setClientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods);

    @JsonSerialize(using = AuthorizationGrantTypeSerializer.class)
    public Set<AuthorizationGrantType> getAuthorizationGrantTypes();

    @JsonDeserialize(using = AuthorizationGrantTypeDeserializer.class)
    public void setAuthorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes);

    public Set<String> getRedirectUris();

    public void setRedirectUris(Set<String> redirectUris);

    public Set<S> getScopes();

    public void setScopes(Set<S> scopes);

    public boolean isRequireAuthenticationConsent();

    public void setRequireAuthenticationConsent(boolean requireAuthenticationConsent);

    public boolean isRequireProofKey();

    public void setRequireProofKey(boolean requireProofKey);

    public Duration getAccessTokenTimeToLive();

    public void setAccessTokenTimeToLive(Duration accessTokenTimeToLive);

    public SignatureAlgorithm getIdTokenSignatureAlgorithm();

    public void setIdTokenSignatureAlgorithm(SignatureAlgorithm idTokenSignatureAlgorithm);

    public Duration getRefreshTokenTimeToLive();

    public void setRefreshTokenTimeToLive(Duration refreshTokenTimeToLive);

    public boolean isReuseRefreshTokens();

    public void setReuseRefreshTokens(boolean reuseRefreshTokens);

    public List<A> getAuthorities();

    public void setAuthorities(List<A> authorities);

    public Set<String> getTags();

    public void setTags(Set<String> tags);

    @JsonIgnore
    @Transient
    public default ClientSettings getClientSettings() {
        return ClientSettings.builder()
                .requireAuthorizationConsent(this.isRequireAuthenticationConsent())
                .requireProofKey(this.isRequireProofKey())
                .build();
    }

    @JsonIgnore
    @Transient
    public default void setClientSettings(ClientSettings clientSettings) {
        this.setRequireAuthenticationConsent(clientSettings.isRequireAuthorizationConsent());
        this.setRequireProofKey(clientSettings.isRequireProofKey());
    }

    @JsonIgnore
    @Transient
    public default TokenSettings getTokenSettings() {
        return TokenSettings.builder()
                .accessTokenTimeToLive(this.getAccessTokenTimeToLive())
                .idTokenSignatureAlgorithm(this.getIdTokenSignatureAlgorithm())
                .refreshTokenTimeToLive(this.getRefreshTokenTimeToLive())
                .reuseRefreshTokens(this.isReuseRefreshTokens())
                .build();
    }

    @JsonIgnore
    @Transient
    public default void setTokenSettings(TokenSettings tokenSettings) {
        this.setAccessTokenTimeToLive(tokenSettings.getAccessTokenTimeToLive());
        this.setIdTokenSignatureAlgorithm(tokenSettings.getIdTokenSignatureAlgorithm());
        this.setRefreshTokenTimeToLive(tokenSettings.getRefreshTokenTimeToLive());
        this.setReuseRefreshTokens(tokenSettings.isReuseRefreshTokens());
    }

    /**
     * Service Account builder
     */
    public interface ServiceAccountBuilder<S, A> {

        public ServiceAccountBuilder id(String id);

        public ServiceAccountBuilder clientId(String clientId);

        public ServiceAccountBuilder hashedClientId(String hashedClientId);

        public ServiceAccountBuilder clientIdIssuedAt(Instant clientIdIssuedAt);

        public ServiceAccountBuilder clientSecret(String clientSecret);

        public ServiceAccountBuilder clientSecretExpiresAt(Instant clientSecretExpiresAt);

        public ServiceAccountBuilder clientName(String clientName);

        public ServiceAccountBuilder clientDescription(String clientDescription);

        public ServiceAccountBuilder clientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods);

        public ServiceAccountBuilder authorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes);

        public ServiceAccountBuilder redirectUris(Set<String> redirectUris);

        public ServiceAccountBuilder scopes(Set<S> scopes);

        public ServiceAccountBuilder requireAuthenticationConsent(boolean requireAuthenticationConsent);

        public ServiceAccountBuilder requireProofKey(boolean requireProofKey);

        public ServiceAccountBuilder accessTokenTimeToLive(Duration accessTokenTimeToLive);

        public ServiceAccountBuilder idTokenSignatureAlgorithm(SignatureAlgorithm idTokenSignatureAlgorithm);

        public ServiceAccountBuilder refreshTokenTimeToLive(Duration refreshTokenTimeToLive);

        public ServiceAccountBuilder reuseRefreshTokens(boolean reuseRefreshTokens);

        public ServiceAccountBuilder authorities(List<A> authorities);

        public ServiceAccountBuilder tags(Set<String> tags);

        public ServiceAccount build();

        /**
         * Sets client settings
         *
         * @param clientSettings Client settings
         * @return Service Account Builder
         */
        public default ServiceAccountBuilder clientSettings(ClientSettings clientSettings) {
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
        public default ServiceAccountBuilder tokenSettings(TokenSettings tokenSettings) {
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
