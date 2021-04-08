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
import net.ati.product.model.dto.ProductByBrandDto;
import net.ati.product.service.ProductByBrandService;

/**
 * @author Nayeemul
 *
 */

@RequestMapping("product-by-brand")
@RestController
@Validated
public class ProductByBrandController {
	
	@Autowired
	private ProductByBrandService productByBrandService;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateProductByBrand(@Valid @RequestBody ProductByBrandDto productByBrandDto) {
		BaseResponse response = productByBrandService.saveAndUpdateProductByBrand(productByBrandDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductByBrandDto>> findProductByBrandList() {
		List<ProductByBrandDto> productByBrandDtos = productByBrandService.findAllProductByBrand();
		return new ResponseEntity<List<ProductByBrandDto>>(productByBrandDtos, HttpStatus.OK);
	}

	@GetMapping("/find/{productByBrandNo}")
	public ResponseEntity<ProductByBrandDto> findProductByBrandByNo(@PathVariable("productByBrandNo") String productByBrandNo) {
		return new ResponseEntity<ProductByBrandDto>(productByBrandService.findProductsByProductByBrandNo(productByBrandNo), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/find-by-lan/{language}")
	public ResponseEntity<List<ProductByBrandDto>> findProductByBrandByLanguage(@PathVariable("language") String language) {
		List<ProductByBrandDto> productByBrandDtos = productByBrandService.findProductByBrandByLanguage(language);
		return new ResponseEntity<List<ProductByBrandDto>>(productByBrandDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public ResponseEntity<ProductByBrandDto> findProductByBrandById(@PathVariable("id") Long id) {
		ProductByBrandDto productByBrandDtos = productByBrandService.findProductByBrandById(id);
		return new ResponseEntity<ProductByBrandDto>(productByBrandDtos, HttpStatus.OK);
	}

}










