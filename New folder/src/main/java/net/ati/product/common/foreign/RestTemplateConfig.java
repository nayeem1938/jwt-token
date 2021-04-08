/**
 * 
 */
package net.ati.product.common.foreign;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nayeemul
 *
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	@LoadBalanced // Load balance between service instances running at different ports.
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
