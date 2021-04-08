/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.ProductByBrandDto;

/**
 * @author Nayeemul
 *
 */
public interface ProductByBrandService {
	
	public BaseResponse saveAndUpdateProductByBrand(ProductByBrandDto productByBrandDto);

	public ProductByBrandDto findProductsByProductByBrandNo(String productByBrandNo);

	public List<ProductByBrandDto> findAllProductByBrand();
	
	public List<ProductByBrandDto> findProductByBrandByLanguage(String language);

	public ProductByBrandDto findProductByBrandById(Long id);
}
