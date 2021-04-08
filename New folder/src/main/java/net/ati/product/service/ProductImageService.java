/**
 * @Since Jan 24, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service.impl
 */
package net.ati.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.ProductImageDto;

/**
 * @author Nayeem
 *
 */
public interface ProductImageService {
	
	
	public BaseResponse saveAndUpdateProductImage(ProductImageDto productImageDto);
	
	public List<ProductImageDto> findImagebyProductNo(String productNo);
	
	public ProductImageDto findImageByNo();
	
	public BaseResponse storeFile(ProductImageDto productImageDto, Map<String, MultipartFile> map);

	/**
	 * @param fileName
	 * @return
	 */
	public Resource loadFileAsResource(String fileName);

}
