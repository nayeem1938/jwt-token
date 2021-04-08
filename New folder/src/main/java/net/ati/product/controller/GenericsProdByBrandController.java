/**
 * @Since Feb 17, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.controller
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
import net.ati.product.model.dto.GenericsProdByBrandDto;
import net.ati.product.service.GenericsProdByBrandService;

/**
 * @author Nayeem
 *
 */

@RestController
@RequestMapping("gen-by-brand")
@Validated
public class GenericsProdByBrandController {
	
	
	@Autowired
	private GenericsProdByBrandService service;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateGenericsProdByBrand(@Valid @RequestBody GenericsProdByBrandDto dto) {
		BaseResponse response = service.saveAndUpdateGenericsProdByBrand(dto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<GenericsProdByBrandDto>> findGenericsTherapyList() {
		List<GenericsProdByBrandDto> dtos = service.findAllGenericsProdByBrand();
		return new ResponseEntity<List<GenericsProdByBrandDto>>(dtos, HttpStatus.OK);
	}

	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<GenericsProdByBrandDto> findGenericsProdByBrandById(@PathVariable("id") Long id) {
		return new ResponseEntity<GenericsProdByBrandDto>(service.findGenericsProdByBrandById(id), HttpStatus.OK);
	}
	



}
