/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.ProductMobileDetailsDto;
import net.ati.product.model.dto.ProductsDto;
import net.ati.product.model.dto.output.SingleProductMobileDetailsDto;

/**
 * @author Nayeemul
 *
 */
public interface ProductsService {
	
	public BaseResponse saveAndUpdateProducts(ProductsDto productsDto);

	public ProductsDto findProductsByProductNo(String productNo);

	public List<ProductsDto> findAllProducts();
	
	public List<ProductsDto> findProductsByLanguage(String language);
	
	public ProductsDto findProductsById(Long id);
	
	List<ProductsDto> findNewProduct();
	
	
	
	/**
	 * From orcl view
	 */
	List<ProductMobileDetailsDto> findAllProductForMobile();
	List<ProductMobileDetailsDto> findProductForMobileByLanguage(String lan);
	SingleProductMobileDetailsDto findProductForMobileByNo(String no);


}
