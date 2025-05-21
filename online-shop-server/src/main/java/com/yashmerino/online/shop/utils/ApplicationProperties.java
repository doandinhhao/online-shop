package com.yashmerino.online.shop.utils;
  
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class that stores properties.
 */
@Component
public class ApplicationProperties {
    /**
     * JWT Secret.
     */
    @Value("${jwt.secret}")
    public String jwtSecret;

    /**
     * Algolia private API key.
     */
    @Value("${algolia.usage:false}")
    public boolean isAlgoliaUsed;

    /**
     * Algolia private API key.
     */
    @Value("${algolia.api.key}")
    public String algoliaApiKey;

    /**
     * Algolia application id.
     */
    @Value("${algolia.app.id}")
    public String algoliaApplicationId;

    /**
     * Algolia index's name.
     */
    @Value("${algolia.index.name}")
    public String algoliaIndexName;
}
