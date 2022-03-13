package oidc.management.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * Default properties for the authorities.
 * 
 * @author Matias Hermosilla
 * @since 08-03-2022
 */
@Data
@Configuration
@ConditionalOnProperty(prefix = "oidc.management.default.authority", name = "enabled", havingValue = "true")
@ConfigurationProperties(prefix = "oidc.management.default.authority")
public class DefaultAuthorityProperties {

    private Boolean enabled;
    
}
