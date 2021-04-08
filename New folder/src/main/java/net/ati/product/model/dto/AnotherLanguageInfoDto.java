/**
 * Jan 6, 2021
 * AnotherLanguageInfoDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnotherLanguageInfoDto {
	
	private String rowLanguageCode;
	
	private AnotherDescriptionDto description;
	

}
