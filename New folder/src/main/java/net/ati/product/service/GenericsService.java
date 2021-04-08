/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.GenericsDto;

/**
 * @author Nayeemul
 *
 */
public interface GenericsService {
	
	public BaseResponse saveAndUpdateGenerics(GenericsDto genericsDto);
	
	public GenericsDto findGenericsByGenericsNo(String genericNo);
	
	public List<GenericsDto> findAllGenerics();
	
	public List<GenericsDto> findGenericsByLanguage(String language);
	
	public GenericsDto findGenericsById(Long id);



}
