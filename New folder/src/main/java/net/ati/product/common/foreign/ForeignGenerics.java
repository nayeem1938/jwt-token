/**
 * 
 */
package net.ati.product.common.foreign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.UserDto;

/**
 * @author Nayeemul
 *
 */

@RefreshScope
@Component
public class ForeignGenerics {
	
	@Autowired
	RestTemplate restTemplate;
	
	public BaseResponse sendGenerics(UserDto userDto)
	{
		String url = "http://oauth-server:9192/auth-api/user/save";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
		ResponseEntity<UserDto> response
			= restTemplate.postForEntity(url,entity, UserDto.class );
		
		System.out.println(response);
		
		return new BaseResponse("Save Success", HttpStatus.CREATED.value());
	}
	
	
	public UserDto findUsrInfoByUsrNameFrmAuth(String username) {
		try {
			String url = "http://oauth-server:9192/auth-api/user/info?username=" + username;
			
			
			ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
			return response.getBody();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}


















