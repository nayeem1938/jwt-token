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
import net.ati.product.model.dto.ProductUnitOfMeasurmentInfoDto;
import net.ati.product.service.ProductUnitOfMeasurmentService;

/**
 * @author Nayeemul
 *
 */

@RequestMapping("/uom")
@RestController
@Validated
public class ProductUnitOfMeasurmentController {
	
	@Autowired
	private ProductUnitOfMeasurmentService productUomService;
	
	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateProductUom(@Valid @RequestBody ProductUnitOfMeasurmentInfoDto productUomDto) {
		BaseResponse response = productUomService.saveAndUpdateProductUom(productUomDto);
		return new ResponseEntity<>(response, response.getCode() == 201 ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
	}


	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductUnitOfMeasurmentInfoDto>> findProductUomList() {
		List<ProductUnitOfMeasurmentInfoDto> therapysDtos = productUomService.findAllUom();
		return new ResponseEntity<List<ProductUnitOfMeasurmentInfoDto>>(therapysDtos, HttpStatus.OK);
	}

	@GetMapping("/find/{uomNo}")
	public ResponseEntity<ProductUnitOfMeasurmentInfoDto> findProductUomByProductUomNo(@PathVariable("uomNo") String uomNo) {
		return new ResponseEntity<ProductUnitOfMeasurmentInfoDto>(productUomService.findUomByUomNo(uomNo), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/find-by-lan/{language}")
	public ResponseEntity<List<ProductUnitOfMeasurmentInfoDto>> findProductUomByLanguage(@PathVariable("language") String language) {
		List<ProductUnitOfMeasurmentInfoDto> therapysDtos = productUomService.findUomByLanguage(language);
		return new ResponseEntity<List<ProductUnitOfMeasurmentInfoDto>>(therapysDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public ResponseEntity<ProductUnitOfMeasurmentInfoDto> findProductUomById(@PathVariable("id") Long id) {
		ProductUnitOfMeasurmentInfoDto therapysDtos = productUomService.findUomById(id);
		return new ResponseEntity<ProductUnitOfMeasurmentInfoDto>(therapysDtos, HttpStatus.OK);
	}

}
