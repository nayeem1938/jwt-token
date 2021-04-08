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
import net.ati.product.model.dto.GenericTherapyDto;
import net.ati.product.service.GenericTherapyService;

/**
 * @author Nayeemul
 *
 */

@RestController
@RequestMapping("generic-therapy")
@Validated
public class GenericTherapyController {
	
	@Autowired
	private GenericTherapyService service;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateGenericTherapy(@Valid @RequestBody GenericTherapyDto dto) {
		BaseResponse response = service.saveAndUpdateGenericTherapy(dto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<GenericTherapyDto>> findGenericsTherapyList() {
		List<GenericTherapyDto> dtos = service.findAllGenericTherapy();
		return new ResponseEntity<List<GenericTherapyDto>>(dtos, HttpStatus.OK);
	}

//	@GetMapping("/find/{genericTherapyNo}")
//	public ResponseEntity<GenericTherapyDto> findGenericTherapyByNo(@PathVariable("genericTherapyNo") String genericTherapyNo) {
//		return new ResponseEntity<GenericTherapyDto>(service.findGenericTherapyByGenericTherapyNo(genericTherapyNo), HttpStatus.OK);
//	}
	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<GenericTherapyDto> findGenericTherapyById(@PathVariable("id") Long id) {
		return new ResponseEntity<GenericTherapyDto>(service.findGenericTherapyById(id), HttpStatus.OK);
	}
	
	
//	@GetMapping("/find-by-lan/{language}")
//	public ResponseEntity<List<GenericTherapyDto>> findGenericTherapyByLanguage(@PathVariable("language") String language) {
//		return new ResponseEntity<List<GenericTherapyDto>>(service.findGenericTherapyByLanguage(language), HttpStatus.OK);
//	}


}
