/**
 * @Since Jan 23, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto.output
 */
package net.ati.product.model.dto.output;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author Nayeem
 *
 */
@Data
public class ProductCategoryOutputDto {
	
	private Long categoryId;
	
	private String rowLanguageCode;
	
	private String categoryNo;
	
	private String categoryName;
	
	private String parentCategoryNo;
	
	private String parentCategoryName;
	
	private Integer userDefineCategoryNo;
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;

	private Long updatedBy;
	
	private Date updatedAt;
	
	private String userPcIp;
	
	private String sessionId;
	
	private String categoryType;
	
	List<ProductCategoryAnotherLanDto> anotherLanDtos;
	

}

