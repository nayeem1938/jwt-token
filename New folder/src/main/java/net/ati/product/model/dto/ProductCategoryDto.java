/**
 * @Since Jan 21, 2021
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
public class ProductCategoryDto {
	
	private IdentityDto categoryId;
	
	private String categoryNo;
	
	private String categoryName;
	
	private String parentCategoryNo;
	
	private Integer userDefineCategoryNo;
	
	private int recordShowFlag; 
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;

	private Long updatedBy;
	
	private Date updatedAt;
	
	private String userPcIp;
	
	private String sessionId;
	
	private String categoryType;
	

	
}
