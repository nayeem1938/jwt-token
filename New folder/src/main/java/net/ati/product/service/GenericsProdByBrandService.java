/**
 * @Since Feb 17, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.service
 */
package net.ati.product.service;

import java.util.List;

import net.ati.product.common.message.BaseResponse;
import net.ati.product.model.dto.GenericsProdByBrandDto;

/**
 * @author Nayeem
 *
 */
public interface GenericsProdByBrandService {
	
	public  BaseResponse saveAndUpdateGenericsProdByBrand(GenericsProdByBrandDto genericTherapyDto);

	public  List<GenericsProdByBrandDto> findAllGenericsProdByBrand();
		
	public  GenericsProdByBrandDto findGenericsProdByBrandById(Long id);


}
