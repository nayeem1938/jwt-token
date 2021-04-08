/**
 * @Since Jan 23, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto.output
 */
package net.ati.product.model.dto.output;

import lombok.Data;

/**
 * @author Nayeem
 *
 */

@Data
public class ProductCategoryAnotherLanDto {
	
	
	private String rowLanguageCode;
	
	private String categoryNo;
	
	private String categoryName;
	
	private String parentCategoryNo;
	
	private String parentCategoryName;

}
