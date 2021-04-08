/**
 * @Since Feb 18, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.TherapyTreeDto;

/**
 * @author Nayeem
 *
 */
public interface TherapyTreeService {
	
	public  BaseResponse saveAndUpdateTherapyTree(TherapyTreeDto therapyTreeDto);

	public  List<TherapyTreeDto> findAllTherapyTree();
		
	public  TherapyTreeDto findTherapyTreeById(Long id);

}
