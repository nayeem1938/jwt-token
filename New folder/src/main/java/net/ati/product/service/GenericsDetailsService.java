/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.GenericsDetailsDto;
import net.ati.product.model.dto.output.GenericsDetailsHeadDto;
import net.ati.product.model.dto.output.GenericsDetailsOutputDto;

/**
 * @author Nayeemul
 *
 */
public interface GenericsDetailsService {

	public  BaseResponse saveAndUpdateGenericsDetails(GenericsDetailsDto GenericsDetailsDto);

	public  GenericsDetailsOutputDto findGenericsDetailsByGenericsDetailsNo(String genericsDetailsNo);

	public  List<GenericsDetailsOutputDto> findAllGenericsDetails();
	
	public List<GenericsDetailsOutputDto> findGenericsDetailsByLanguage(String language);

	public GenericsDetailsOutputDto findGenericsDetailsById(Long id); 
	
	public  List<GenericsDetailsHeadDto> findAllGenericsDetailsHead();
}
