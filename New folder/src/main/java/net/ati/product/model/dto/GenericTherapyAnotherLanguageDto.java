/**
 * @Since Jan 9, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 */
package net.ati.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nayeem
 *
 */

@Data
@AllArgsConstructor
public class GenericTherapyAnotherLanguageDto {
	
	private String rowLanguageCode;
	
	private GenericsDto generic;

	private TherapysDto therapy;

}
