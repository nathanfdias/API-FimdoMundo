package br.org.serratec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.serratec.dto.EnderecoDTO;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.model.Produto;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .ignoredParameterTypes(Produto.class, Cliente.class, Endereco.class, EnderecoDTO.class);
    }
}
