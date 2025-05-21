package com.yashmerino.online.shop.swagger;

  
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration class to enable swagger documentation for
 * the REST API.
 */
@Configuration
@Profile({"!prod && swagger"})
public class SwaggerConfig {

    /**
     * The security scheme name used for the rest controllers.
     */
    public static final String SECURITY_SCHEME_NAME = "Online Shop";

    /**
     * @return Configuration for the Open API.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme()))
                .info(new Info()
                        .title("Online Shop API")
                        .version("0.1"));
    }

    /**
     * @return A security scheme to enable adding the JWT token in the HTTP header.
     */
    private SecurityScheme securityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.name(SECURITY_SCHEME_NAME);
        securityScheme.type(Type.HTTP);
        securityScheme.scheme("bearer");
        securityScheme.bearerFormat("JWT");
        return securityScheme;
    }

}
