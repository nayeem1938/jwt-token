/**
 * @Since Jan 9, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 * @FileName ProductUomAnotherLanguageinfo
 */
package net.ati.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUomAnotherLanguageinfo {
	
	private String rowLanguageCode;
	
	private String unitOfMeasurmentName;
		
	private String unitOfMeasurmentComment;

	private String productStrengthFlag;

}
