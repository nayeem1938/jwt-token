/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.security.config;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger.web.SecurityConfiguration;

/**
 * @author Nayeemul
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	public static final String securitySchemaOAuth2 = "oauth2schema";
	public static final String authorizationScopeGlobal = "global";
	public static final String authorizationScopeGlobalDesc = "accessEverything";

	
	@Bean
	public Docket productApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("net.ati.product.controller"))
				.paths(PathSelectors.any()).build()
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Arrays.asList(securitySchema()))
						.apiInfo(apiInfo());
				
	}
	
	@Bean
	public SecurityScheme apiKey()
	{
		return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
	}
	
	@Bean
	public SecurityScheme apiCookiueKey()
	{
		return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
	}

	private OAuth securitySchema()
	{
		List<AuthorizationScope> authorizationScopeList = newArrayList();
		authorizationScopeList.add(new AuthorizationScope("READ", "read all"));
		authorizationScopeList.add( new AuthorizationScope("TRUSTED", "trust all"));
		authorizationScopeList.add(new AuthorizationScope("WRITE", "write all"));
		List<GrantType> grantTypes = newArrayList();
		GrantType passwordCredentialGrant = new ResourceOwnerPasswordCredentialsGrant("http://192.168.0.91:9191/auth-api/oauth/token");
		grantTypes.add(passwordCredentialGrant);
		
		return new OAuth("oauth2", authorizationScopeList, grantTypes);
		
		
	}
	
	
	private SecurityContext securityContext()
	{
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	
	private List<SecurityReference> defaultAuth()
	{
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		
		authorizationScopes[0] = new AuthorizationScope("READ", "read all");
		authorizationScopes[1] = new AuthorizationScope("TRUSTED", "trust all");
		authorizationScopes[2] = new AuthorizationScope("WRITE", "write all");
		
		return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
				
	}
	
	@Bean
	public SecurityConfiguration security(){
		
		return new SecurityConfiguration(clientId, clientSecret, "", "", 
				"Bearer access token", ApiKeyVehicle.HEADER,
				HttpHeaders.AUTHORIZATION, "");
	}
	
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("ATI MICROSERVICE")
				.description("Author by Nayeemul Islam")
				.termsOfServiceUrl("https://www.atilimited.net/")
				.contact(new Contact("ATI Limited", "https://www.atilimited.net/", "nayeemul@atilimited.net"))
				.license("Open Source").licenseUrl("https://www.atilimited.net").version("1.0.0").build();

	}
	
	
	
//	@Value("${security.oauth2.client.client-id}")
//	private String clientId;
//
//	@Value("${security.oauth2.client.client-secret}")
//	private String clientSecret;
//
//	public static final String securitySchemaOAuth2 = "oauth2schema";
//	public static final String authorizationScopeGlobal = "global";
//	public static final String authorizationScopeGlobalDesc = "accessEverything";
//
//	/**
//	 *
//	 * @return Docket
//	 */
//
//	@Bean
//	public Docket productApi() {
//
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("net.ati.product.controller")).paths(PathSelectors.any()).build()
//				.securityContexts(Collections.singletonList(securityContext()))
//				.securitySchemes(Arrays.asList(securitySchema())).apiInfo(apiInfo());
//
//	}
//
//	private OAuth securitySchema()
//	{
//		List<AuthorizationScope> authorizationScopeList = newArrayList();
//		authorizationScopeList.add(new AuthorizationScope("READ", "read all"));
//		authorizationScopeList.add( new AuthorizationScope("TRUSTED", "trust all"));
//		authorizationScopeList.add(new AuthorizationScope("WRITE", "write all"));
//		List<GrantType> grantTypes = newArrayList();
//		GrantType passwordCredentialGrant = new ResourceOwnerPasswordCredentialsGrant("http://192.168.0.91:9191/auth-api/oauth/token");
//		grantTypes.add(passwordCredentialGrant);
//		
//		return new OAuth("oauth2", authorizationScopeList, grantTypes);
//		
//		
//	}
//
//	private SecurityContext securityContext()
//	{
//		return SecurityContext.builder().securityReferences(defaultAuth()).build();
//	}
//
//	private List<SecurityReference> defaultAuth()
//	{
//		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
//		
//		authorizationScopes[0] = new AuthorizationScope("READ", "read all");
//		authorizationScopes[1] = new AuthorizationScope("TRUSTED", "trust all");
//		authorizationScopes[2] = new AuthorizationScope("WRITE", "write all");
//		
//		return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
//				
//	}
//
//	@Bean
//	public SecurityConfiguration security(){
//		
//		return new SecurityConfiguration(clientId, clientSecret, "", "", 
//				"Bearer access token", ApiKeyVehicle.HEADER,
//				HttpHeaders.AUTHORIZATION, "");
//	}
//	/**
//	 *
//	 * @return ApiInfo
//	 */
//	private ApiInfo apiInfo()
//	{
//		return new ApiInfoBuilder().title("ATI MICROSERVICE")
//				.description("Author by Nayeemul Islam")
//				.termsOfServiceUrl("https://www.atilimited.net/")
//				.contact(new Contact("ATI Limited", "https://www.atilimited.net/", "nayeemul@atilimited.net"))
//				.license("Open Source").licenseUrl("https://www.atilimited.net").version("1.0.0").build();
//
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
