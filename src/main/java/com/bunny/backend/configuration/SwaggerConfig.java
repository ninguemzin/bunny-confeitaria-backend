package com.bunny.backend.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
    OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Bunny Confeitaria - API REST")
                .description("API do sistema Bunny Confeitaria, responsável pelo gerenciamento de usuários, pedidos e produtos.")
                .version("v0.0.1")
                .license(new License()
                    .name("Licença Proprietária - Todos os Direitos Reservados")
                    .url("https://github.com/ninguemzin/bunny-confeitaria-backend/LICENSE"))
                .contact(new Contact()
                    .name("Emerson Marques")
                    .url("https://github.com/ninguemzin")
                    .email("ALM1427@hotmail.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Documentação no GitHub")
                .url("https://github.com/ninguemzin/bunny-confeitaria-backend"));
    }


	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
}