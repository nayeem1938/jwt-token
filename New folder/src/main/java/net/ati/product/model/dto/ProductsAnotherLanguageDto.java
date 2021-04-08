/**
 * @Since Jan 9, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 */
package net.ati.product.model.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeem
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsAnotherLanguageDto {
	
	private String rowLanguageCode;
	
	private String productNo;

	private String productName;
	
	private String productDescription;
	
	private String parentProductNo;
	
	private String parentProductName;
	
	private ProductUnitOfMeasurmentInfoDto productUnitOfMeasurment;
	
	private GenericsDto generic;

	private ProductTypeDto productType;
	
	private String productStrength;
	
	private int strengthUnitId;
	
	private int productTotalStrength;
	
	private String productFlag;
	
	private String userDefineProductNo;
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;
	
	private Long updatedBy;
	
	private Date updatedAt;
	
	private int ShowProductNewFlag;
	
	private String productImageLength;
	
	private String userPcIp;
	
	private String sessionId;
	
	private List<ProductsAnotherLanguageDto> anotherLanguageInfo;

}
