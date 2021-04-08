/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.GenericTherapyDto;

/**
 * @author Nayeemul
 *
 */
public interface GenericTherapyService {
	
	public  BaseResponse saveAndUpdateGenericTherapy(GenericTherapyDto genericTherapyDto);

//	public  GenericTherapyDto findGenericTherapyByGenericTherapyNo(String genericTherapyNo);	
//	public  List<GenericTherapyDto> findGenericTherapyByLanguage(String language);
	
	public  GenericTherapyDto findGenericTherapyById(Long id);

	/**
	 * @return
	 */
	public List<GenericTherapyDto> findAllGenericTherapy();


}
