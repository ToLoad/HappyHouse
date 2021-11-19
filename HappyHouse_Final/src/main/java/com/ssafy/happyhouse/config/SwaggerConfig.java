package com.ssafy.happyhouse.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8000/swagger-ui.html
	
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private String version = "V1";
	private String title = "SSAFY HAPPYHOUSE API " + version;
	
	@Bean
	public Docket postsApi() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("access-token")
						.description("Access Token")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build();
		
		List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(aParameterBuilder.build());
		
//		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ssafyHappyHouseAPI")
				.globalOperationParameters(aParameters)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.happyhouse.controller"))
				.paths(postPaths())
				.build();
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,responseMessages);
	}
	
	private Predicate<String> postPaths() {
		return PathSelectors.any();
//		return PathSelectors.ant("/**")
//		return or(regex("/user/.*"), regex("/article/.*"), regex("/memo/.*"));
//		return regex("/admin/.*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description("SSAFY API Reference for Developers")
				.termsOfServiceUrl("https://edu.ssafy.com")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("SSAFY License")
				.licenseUrl("ssafy@ssafy.com").version("1.0").build();
	}

}


