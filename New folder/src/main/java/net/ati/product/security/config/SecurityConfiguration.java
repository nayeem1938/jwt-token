/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @author Nayeemul
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
        							"/swagger-ui.html", "/webjars/**", "/swagger/**", "/prod-img/**");
    }
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		  http.cors().and().csrf().disable()
		  .sessionManagement().disable()
		  .authorizeRequests().antMatchers("/prod-img/downloadFile/**").permitAll().and()
		  .authorizeRequests().antMatchers("/prod-img/getFile/**").permitAll(); 
	}
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
//        							"/swagger-ui.html", "/webjars/**", "/swagger/**");
//    }
//	
//	@Override 
//	protected void configure(HttpSecurity http) throws Exception {
//		  http.cors().and().csrf().disable()
//		  .sessionManagement().disable()
//		  .authorizeRequests().antMatchers("/**").permitAll(); 
//	}
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**");
//	}
}

