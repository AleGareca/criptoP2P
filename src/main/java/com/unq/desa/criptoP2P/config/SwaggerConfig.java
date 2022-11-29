package com.unq.desa.criptoP2P.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;



@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class SwaggerConfig implements WebMvcConfigurer {

   /* @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.unq.desa.criptoP2P.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(schemeList())
                .securityContexts(securityContext());
    }*/
   @Bean
   public OpenAPI customOpenAPI() {
       return new OpenAPI()
               .info(new Info()
                       .title("Crypto P2P API")
                       .version("3.0.7")
                       .description("Cripto P2P. Universidad Nacional de Quilmes")
                       .termsOfService("http://swagger.io/terms/"));
   }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Cripto P2P. Universidad Nacional de Quilmes")
                .description("Back-end con Tecnologías de Código Abierto (SPRING)."
                        + "https://github.com/AleGareca/criptoP2P").build();
    }

    private List<SecurityContext> securityContext() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{new AuthorizationScope(
                "global", "accessEverything")};;
        List<SecurityReference> bearerSecurityReferences = Arrays.asList(new SecurityReference("Bearer",
                authorizationScopes));
        return Arrays.asList(
                SecurityContext.builder().securityReferences(
                        bearerSecurityReferences).forPaths(PathSelectors.ant("/**")).build());
    }
}
