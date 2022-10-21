package com.straumann.blr.cloudapim.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@AllArgsConstructor
public class SwaggerConfig {

    /*   private final SwaggerProperties swaggerProperties;*/

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("cloud-straumann")
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .protocols(Sets.newHashSet("https", "http"))
                .apiInfo(metadata())
                .securityContexts(Arrays.asList(securityContext()));
    }


    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("Cloud Apim Service")
                .description("")
                .termsOfServiceUrl("https://www.google.com")
                .contact(getContact())
                .license("Public License")
                .version("0.1").build();
    }

    private springfox.documentation.service.Contact getContact() {

        return new springfox.documentation.service.Contact("Rohit Mysore Lakshmikanth", "linkedin.com/in/mlrohit", "rohit.lakshmikanth@straumann.com");
    }

    private ApiKey apiKey() {
        return new ApiKey("cloud-apim-service", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("cloud-apim-service",
                authorizationScopes));
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Cloud APIM Service")
                .description("Swagger 2.0 API for Cloud APIM Service Product")
                .version("v1")
                .build();
    }


    /**
     * Created by mtumilowicz on 2018-07-13.
     */
    /*@Configuration
    @ConfigurationProperties("swagger")
    @EnableConfigurationProperties(SwaggerProperties.class)
    @Getter
    @Setter // otherwise exception during boot: Reason: No setter found for property: enabled
    public class SwaggerProperties {
        @NotBlank
        private boolean enabled;
    }*/
}