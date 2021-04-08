/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.GenericsDto;
import net.ati.product.service.GenericsService;


/**
 * @author Nayeemul
 *
 */

@RequestMapping("/generics")
@RestController
@Validated
public class GenericsController {

	@Autowired
	private GenericsService genericsService;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateGenerics(@Valid @RequestBody GenericsDto genericsDto) {
		BaseResponse response = genericsService.saveAndUpdateGenerics(genericsDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<GenericsDto>> findGenericsList() {
		List<GenericsDto> genericsDtos = genericsService.findAllGenerics();
		return new ResponseEntity<List<GenericsDto>>(genericsDtos, HttpStatus.OK);
	}

	@GetMapping("/find/{genericNo}")
	public ResponseEntity<GenericsDto> findGenericsByNo(@PathVariable("genericNo") String genericNo) {
		return new ResponseEntity<GenericsDto>(genericsService.findGenericsByGenericsNo(genericNo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-by-lan/{language}")
	public ResponseEntity<List<GenericsDto>> findGenericsByLanguage(@PathVariable("language") String language) {
		List<GenericsDto> genericsDtos = genericsService.findGenericsByLanguage(language);
		return new ResponseEntity<List<GenericsDto>>(genericsDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public ResponseEntity<GenericsDto> findGenericsById(@PathVariable("id") Long id) {
		GenericsDto genericsDto = genericsService.findGenericsById(id);
		return new ResponseEntity<GenericsDto>(genericsDto, HttpStatus.OK);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	

}
