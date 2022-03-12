package oidc.management.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * Default properties for the authorities.
 * 
 * @author Matias Hermosilla
 * @since 08-03-2022
 */
@Data
@ConfigurationProperties(prefix = "oidc.management.authority")
public class DefaultAuthorityProperties {
    private Boolean enabled;
}
