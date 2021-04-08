/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.ProductTypeDto;

/**
 * @author Nayeemul
 *
 */
public interface ProductTypeService {

	public BaseResponse saveAndUpdateProductType(ProductTypeDto productTypeDto);

	public ProductTypeDto findProductTypeByProductTypeNo(String productTypeNo);

	public List<ProductTypeDto> findAllProductType();
	
	public List<ProductTypeDto> findProductTypeByLanguage(String language);

	public ProductTypeDto findProductTypeById(Long id);


}
