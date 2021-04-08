/**
 * 
 */
package net.ati.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ati.product.common.foreign.ForeignGenerics;
import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.UserDto;

/**
 * @author Nayeemul
 *
 */

@RequestMapping("/foreign")
@RestController
public class ForeignController {
	@Autowired
	ForeignGenerics foreignGenerics;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateGenerics(@RequestBody UserDto userDto) {
		BaseResponse response = foreignGenerics.sendGenerics(userDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = { "/info" })
	public UserDto getUserInfo(@RequestParam("name") String name) {
		UserDto userDto = foreignGenerics.findUsrInfoByUsrNameFrmAuth(name);
		
		return userDto;
	}

}
