/**
 * @Since Jan 23, 2021
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
import net.ati.product.model.dto.ProductCategoryDto;
import net.ati.product.model.dto.output.ProductCategoryOutputDto;
import net.ati.product.service.ProductCategoryService;

/**
 * @author Nayeem
 *
 */
@RequestMapping("prod-catg")
@RestController
@Validated 
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService categoryService; 

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateProductType(@Valid @RequestBody ProductCategoryDto productCategoryDto) {
		BaseResponse response = categoryService.saveAndUpdateProductCategory(productCategoryDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductCategoryOutputDto>> findProductTypeList() {
		List<ProductCategoryOutputDto> productCategoryDtos = categoryService.findAllProductCategory();
		return new ResponseEntity<List<ProductCategoryOutputDto>>(productCategoryDtos, HttpStatus.OK);
	}

	@GetMapping("/find/{prodCatgNo}")
	public ResponseEntity<ProductCategoryOutputDto> findProductCategoryByCategoryNo(@PathVariable("prodCatgNo") String categoryNo) {
		return new ResponseEntity<ProductCategoryOutputDto>(categoryService.findProductCategoryByProductCategoryNo(categoryNo), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/find-by-lan/{language}")
	public ResponseEntity<List<ProductCategoryOutputDto>> findProductTypeByLanguage(@PathVariable("language") String language) {
		List<ProductCategoryOutputDto> productCategoryDtos = categoryService.findProductCategoryByLanguage(language);
		return new ResponseEntity<List<ProductCategoryOutputDto>>(productCategoryDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public ResponseEntity<ProductCategoryOutputDto> findProductTypeById(@PathVariable("id") Long id) {
		ProductCategoryOutputDto productCategoryDtos = categoryService.findProductCategoryById(id);
		return new ResponseEntity<ProductCategoryOutputDto>(productCategoryDtos, HttpStatus.OK);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	

}

