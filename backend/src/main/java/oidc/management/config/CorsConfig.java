package oidc.management.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Cors configuration.
 * 
 * @author Matías Hermosilla
 * @since 30-01-2022
 * @see CorsConfiguration
 * @see CorsConfigurationSource
 * @see UrlBasedCorsConfigurationSource
 */
@Configuration
public class CorsConfig {
    
    @Primary
    @Bean
    public CorsConfigurationSource configurationSource() {
        // Create CorsConfiguration
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));


        // Create configuration source
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        // Set cors configuration
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        // Return configuration source
        return corsConfigurationSource;
    }

}
