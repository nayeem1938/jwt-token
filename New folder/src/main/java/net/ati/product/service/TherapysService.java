/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.TherapysDto;

/**
 * @author Nayeemul
 *
 */
public interface TherapysService {

	public BaseResponse saveAndUpdateTherapys(TherapysDto therapysDto);

	public TherapysDto findTherapysByTherapyNo(String therapyNo);

	public List<TherapysDto> findAllTherapys();
	
	public List<TherapysDto> findByTherapysLanguage(String language);
	
	public TherapysDto findByTherapysId(Long id);


}
