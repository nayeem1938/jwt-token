/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */

package net.ati.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DrugProductMicroserviceResourseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugProductMicroserviceResourseServerApplication.class, args);
		System.out.println("Product Service Started");
		
		
		//@Column(name = "ip_address")
//		String ipAddress = InetAddress.getLoopbackAddress().getHostAddress();
//		System.out.println(ipAddress);
//		System.out.println(new WebUtils().getClientIp());

	}

}
