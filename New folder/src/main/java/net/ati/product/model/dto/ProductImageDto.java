/**
 * @Since Jan 24, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 */
package net.ati.product.model.dto;

import java.util.Date;

import lombok.Data;


/**
 * @author Nayeem
 *
 */

@Data
public class ProductImageDto {
	
	private Long imageId;
	
	private String rowLanguageCode;
	
	private String imageNo;
		
	private String imageLink;
	
	private String productNo;
	
	private String productName;
	
	private String productDescripion;
	
	private String productByBrandNo;
	
	private String productByBrandName;
	
	private String userDefineImageNo;
	
	private int recordShowFlag; 
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;
	
	private Long updatedBy;
	
	private Date updatedAt;
	
	private String userPcIp;

	private String sessionId;
}
