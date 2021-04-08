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
import net.ati.product.model.dto.ProductTypeDto;
import net.ati.product.service.ProductTypeService;

/**
 * @author Nayeemul
 *
 */

@RequestMapping("product-type")
@RestController
@Validated 
public class ProductTypeController {
	
	@Autowired
	private ProductTypeService productTypeService;

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateProductType(@Valid @RequestBody ProductTypeDto productTypeDto) {
		BaseResponse response = productTypeService.saveAndUpdateProductType(productTypeDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductTypeDto>> findProductTypeList() {
		List<ProductTypeDto> productTypeDtos = productTypeService.findAllProductType();
		return new ResponseEntity<List<ProductTypeDto>>(productTypeDtos, HttpStatus.OK);
	}

	@GetMapping("/find/{productTypeNo}")
	public ResponseEntity<ProductTypeDto> findProductTypeByNo(@PathVariable("productTypeNo") String productTypeNo) {
		return new ResponseEntity<ProductTypeDto>(productTypeService.findProductTypeByProductTypeNo(productTypeNo), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/find-by-lan/{language}")
	public ResponseEntity<List<ProductTypeDto>> findProductTypeByLanguage(@PathVariable("language") String language) {
		List<ProductTypeDto> productTypeDtos = productTypeService.findProductTypeByLanguage(language);
		return new ResponseEntity<List<ProductTypeDto>>(productTypeDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public ResponseEntity<ProductTypeDto> findProductTypeById(@PathVariable("id") Long id) {
		ProductTypeDto productTypeDtos = productTypeService.findProductTypeById(id);
		return new ResponseEntity<ProductTypeDto>(productTypeDtos, HttpStatus.OK);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	

}
