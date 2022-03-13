package oidc.management.properties;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import lombok.Data;
import oidc.management.util.DefaultServiceAccountSeeder;

/**
 * Default service account properties.
 * 
 * @author Matias Hermosilla
 * @since 20-02-2022
 * @see DefaultServiceAccountSeeder
 */
@Validated
@Data
@Configuration
@ConditionalOnProperty(prefix = "oidc.management.default.service-account", name = "enabled", havingValue = "true")
@ConfigurationProperties(prefix = "oidc.management.default.service-account")
public class DefaultServiceAccountProperties {

    private Boolean enabled;
    
    @NotEmpty
    private String clientId;

    @NotEmpty
    private String clientSecret;

    @NotNull
    private Set<String> redirectUris;
    
}
