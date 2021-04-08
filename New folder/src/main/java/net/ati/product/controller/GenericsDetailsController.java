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
import net.ati.product.model.dto.GenericsDetailsDto;
import net.ati.product.model.dto.output.GenericsDetailsHeadDto;
import net.ati.product.model.dto.output.GenericsDetailsOutputDto;
import net.ati.product.service.GenericsDetailsService;

/**
 * @author Nayeemul
 *
 */

@RestController
@Validated
@RequestMapping("/generics-details")
public class GenericsDetailsController {
	
	@Autowired
	private GenericsDetailsService service;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateGenericsDetails(@Valid @RequestBody GenericsDetailsDto dto) {
		BaseResponse response = service.saveAndUpdateGenericsDetails(dto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<GenericsDetailsOutputDto>> findGenericsDetailsList() {
		List<GenericsDetailsOutputDto> dtos = service.findAllGenericsDetails();
		return new ResponseEntity<List<GenericsDetailsOutputDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/find/{genericDetailsNo}")
	public ResponseEntity<GenericsDetailsOutputDto> findGenericsDetailsByGenericsDetailsNo(@PathVariable("genericDetailsNo") String genericDetailsNo) {
		return new ResponseEntity<GenericsDetailsOutputDto>(service.findGenericsDetailsByGenericsDetailsNo(genericDetailsNo), HttpStatus.OK);
	}
	
	@GetMapping("/find-by-lan/{language}")
	public ResponseEntity<List<GenericsDetailsOutputDto>> findGenericsDetailsByLanguage(@PathVariable("language") String language) {
		return new ResponseEntity<List<GenericsDetailsOutputDto>>(service.findGenericsDetailsByLanguage(language), HttpStatus.OK);
	}
	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<GenericsDetailsOutputDto> findGenericsDetailsById(@PathVariable("id") Long id) {
		return new ResponseEntity<GenericsDetailsOutputDto>(service.findGenericsDetailsById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/all-head")
	public ResponseEntity<List<GenericsDetailsHeadDto>> findGenericsDetailsHeadList() {
		List<GenericsDetailsHeadDto> dtos = service.findAllGenericsDetailsHead();
		return new ResponseEntity<List<GenericsDetailsHeadDto>>(dtos, HttpStatus.OK);
	}
	

}
