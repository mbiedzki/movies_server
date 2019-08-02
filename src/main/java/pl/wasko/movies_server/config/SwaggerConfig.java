package pl.wasko.movies_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.wasko.movies_server.REST"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(metaData());
    }

    //this is to generate API documentation
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Wasko Movie REST Server")
                .description("API documentation for Wasko Movie REST Server")
                .version("1.0")
                .license("free for testing")
                .licenseUrl("https://www/wasko.pl")
                .contact(new Contact("Michal Biedzki", "https://www.wasko.pl", "m.biedzki@wasko.pl"))
                .build();
    }

}