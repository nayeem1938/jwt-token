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
import net.ati.product.model.dto.ProductMobileDetailsDto;
import net.ati.product.model.dto.ProductsDto;
import net.ati.product.model.dto.output.SingleProductMobileDetailsDto;
import net.ati.product.service.ProductsService;

/**
 * @author Nayeemul
 *
 */

@RequestMapping("/product")
@RestController
@Validated
public class ProductsController {
	
	@Autowired
	private ProductsService productsService;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateProducts(@Valid @RequestBody ProductsDto productsDto) {
		BaseResponse response = productsService.saveAndUpdateProducts(productsDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductsDto>> findProductsList() {
		List<ProductsDto> productsDtos = productsService.findAllProducts();
		return new ResponseEntity<List<ProductsDto>>(productsDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/new")
	public ResponseEntity<List<ProductsDto>> findNewProductsList() {
		List<ProductsDto> productsDtos = productsService.findNewProduct();
		return new ResponseEntity<List<ProductsDto>>(productsDtos, HttpStatus.OK);
	}

	@GetMapping("/find/{productNo}")
	public ResponseEntity<ProductsDto> findProductsByNo(@PathVariable("productNo") String productNo) {
		return new ResponseEntity<ProductsDto>(productsService.findProductsByProductNo(productNo), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/find-by-lan/{language}")
	public ResponseEntity<List<ProductsDto>> findProductsByLanguage(@PathVariable("language") String language) {
		List<ProductsDto> productsDtos = productsService.findProductsByLanguage(language);
		return new ResponseEntity<List<ProductsDto>>(productsDtos, HttpStatus.OK);
	}

	
	@GetMapping(value = "/find-by-id/{id}")
	public ResponseEntity<ProductsDto> findProductsById(@PathVariable("id") Long id) {
		ProductsDto productsDtos = productsService.findProductsById(id);
		return new ResponseEntity<ProductsDto>(productsDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<ProductMobileDetailsDto>> findAllProductsForMobile() {
		return new ResponseEntity<List<ProductMobileDetailsDto>>(productsService.findAllProductForMobile(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-by-lan/{lan}")
	public ResponseEntity<List<ProductMobileDetailsDto>> findProductsForMobileByLanguage(@PathVariable("lan") String lan) {
		return new ResponseEntity<List<ProductMobileDetailsDto>>(productsService.findProductForMobileByLanguage(lan), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-by-no/{no}")
	public ResponseEntity<SingleProductMobileDetailsDto> findProductsForMobileByProductNo(@PathVariable("no") String no) {
		return new ResponseEntity<SingleProductMobileDetailsDto>(productsService.findProductForMobileByNo(no), HttpStatus.OK);
	}
}





















