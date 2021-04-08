/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.ProductUnitOfMeasurmentInfoDto;

/**
 * @author Nayeemul
 *
 */
public interface ProductUnitOfMeasurmentService {
	
	public BaseResponse saveAndUpdateProductUom(ProductUnitOfMeasurmentInfoDto productUomDto);

	public ProductUnitOfMeasurmentInfoDto findUomByUomNo(String uomNo);

	public List<ProductUnitOfMeasurmentInfoDto> findAllUom();
	
	public List<ProductUnitOfMeasurmentInfoDto> findUomByLanguage(String language);

	public ProductUnitOfMeasurmentInfoDto findUomById(Long id);

}
