package com.example.fcmpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "EnergyX BEMS API", version = "1.0", description = "This is the API documentation project."))
public class OpenApiConfig {
	@Bean
	public OpenAPI openAPI() {
		// SecuritySecheme명
		String jwtSchemeName = "Authorization";
		// API 요청헤더에 인증정보 포함
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
		// SecuritySchemes 등록
		Components components = new Components()
			.addSecuritySchemes(jwtSchemeName, new SecurityScheme()
				.name(jwtSchemeName)
				.type(SecurityScheme.Type.HTTP) // HTTP 방식
				.scheme("bearer")
				.bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

		return new OpenAPI()
			.addSecurityItem(securityRequirement)
			.components(components);
	}


}