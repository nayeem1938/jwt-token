/**
 * @Since Jan 21, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.ProductCategoryDto;
import net.ati.product.model.dto.output.ProductCategoryOutputDto;

/**
 * @author Nayeem
 *
 */
public interface ProductCategoryService {
	
	public BaseResponse saveAndUpdateProductCategory(ProductCategoryDto productCategoryDto);

	public ProductCategoryOutputDto findProductCategoryByProductCategoryNo(String categoryNo);

	public List<ProductCategoryOutputDto> findAllProductCategory();
	
	public List<ProductCategoryOutputDto> findProductCategoryByLanguage(String language);

	public ProductCategoryOutputDto findProductCategoryById(Long id);


}
