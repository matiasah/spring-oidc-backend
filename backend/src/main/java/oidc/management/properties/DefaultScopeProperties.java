package oidc.management.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * Default properties for the scopes.
 * 
 * @author Matias Hermosilla
 * @since 08-03-2022
 */
@Data
@ConfigurationProperties(prefix = "oidc.management.scope")
public class DefaultScopeProperties {
    private Boolean enabled;
}
