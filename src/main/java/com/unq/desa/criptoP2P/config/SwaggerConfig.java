package com.unq.desa.criptoP2P.config;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
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
    }

    private List<SecurityScheme> schemeList() {
        return Arrays.asList(new BasicAuth("Basic"),
                new ApiKey("Bearer", "Authorization", "header"));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Máster en Ingeniería Web. Universidad Politécnica de Madrid")
                .description("BETCA. Back-end con Tecnologías de Código Abierto (SPRING)."
                        + "https://github.com/miw-upm/betca-tpv-spring").build();
    }

    private List<SecurityContext> securityContext() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{new AuthorizationScope(
                "global", "accessEverything")};
        List<SecurityReference> basicSecurityReferences = Arrays.asList(new SecurityReference("Basic",
                authorizationScopes));
        List<SecurityReference> bearerSecurityReferences = Arrays.asList(new SecurityReference("Bearer",
                authorizationScopes));
        return Arrays.asList(
                SecurityContext.builder().securityReferences(
                        bearerSecurityReferences).forPaths(PathSelectors.ant("/**")).build());
    }
}
