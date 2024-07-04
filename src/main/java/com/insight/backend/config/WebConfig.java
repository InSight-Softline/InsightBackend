package com.insight.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class responsible for setting up Cross-Origin Resource Sharing (CORS) settings.
 * <p>
 * This class is annotated with {@link Configuration} to indicate that it contains bean definitions
 * and provides configuration for the Spring application context.
 * </p>
 * <p>
 * It configures global CORS settings, which determine how resources are shared between different origins.
 * The settings are applied through the {@link WebMvcConfigurer} interface.
 * </p>
 * <p>
 * The CORS settings are dynamically configured based on properties defined in the application's environment.
 * </p>
 * 
 * @author Amani Hamad
 * @version 1.0
 */
@Configuration
public class WebConfig {

    @Autowired
    private Environment env;

    /**
     * Provides a {@link WebMvcConfigurer} bean that configures CORS settings for the application.
     * <p>
     * This method creates an instance of {@link WebMvcConfigurer} that overrides the
     * {@link WebMvcConfigurer#addCorsMappings(CorsRegistry)} method to set up CORS mappings.
     * </p>
     * <p>
     * The CORS settings are based on the property {@code cors.urls} defined in the application properties.
     * If the property is not set, it defaults to allowing requests from all origins ("*").
     * </p>
     * 
     * @return a {@link WebMvcConfigurer} instance with the configured CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                // Retrieve the allowed origins from the application properties. If not set, default to "*".
                String allowedOrigins = env.getProperty("cors.urls", "*");
                
                // Configure CORS settings
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins.split(","))
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
    }
}