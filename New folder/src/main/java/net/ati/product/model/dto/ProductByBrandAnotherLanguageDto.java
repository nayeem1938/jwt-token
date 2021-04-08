/**
 * @Since Jan 9, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 */
package net.ati.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeem
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductByBrandAnotherLanguageDto {
	
	private String rowLanguageCode;
	
	private String brandName;
	
//	private GenericsDto generic;

}
