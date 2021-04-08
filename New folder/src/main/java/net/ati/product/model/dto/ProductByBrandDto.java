/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeemul
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductByBrandDto {
	
	private IdentityDto productByBrandId;
	
	private String productByBrandNo;

	private String brandName;
	
//	private GenericsDto generic;
	
	private int recordShowFlag ;
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private String createdAt;
	
	private Long updatedBy;
	
	private String updatedAt;
	
	private String userPcIp;
	
	private String sessionId;
	
	private List<ProductByBrandAnotherLanguageDto> anotherLanguageInfos;

}
