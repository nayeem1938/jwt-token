/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Nayeemul
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

private static final String RESOURCE_ID = "ati-product-rs";
private static final String SECURE_READ_SCOPE = "#oauth2.hasScope('READ')";
private static final String SECURE_WRITE_SCOPE = "#oauth2.hasScope('WRITE')";
private static final String SECURE_PATTERN = "/**";

@Override
public void configure(HttpSecurity security) throws Exception
{
	security.cors().and().csrf().disable()
	.sessionManagement().disable()
	.authorizeRequests()
//	.antMatchers("/medicine/category/list").permitAll()
	.and()
	.requestMatchers()
	.antMatchers(SECURE_PATTERN).and().authorizeRequests()
	.antMatchers(HttpMethod.POST, SECURE_PATTERN)
	.access(SECURE_WRITE_SCOPE)
	.anyRequest().access(SECURE_READ_SCOPE);
}

@Override
public void configure(ResourceServerSecurityConfigurer configurer)
{
	configurer.resourceId(RESOURCE_ID);
}


}





















