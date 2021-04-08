/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.dto;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeemul
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericsDto {
	
	private IdentityDto genericsId;

	private String genericsNo;

	private String genericsName;

	private String genericNameByIngredientName;

	private int recordShowFlag;

	private int activeStatusFlag;

	private Long userDefineSerialNumber;

	private Long createdBy;

	private Date createdAt;

	private Long updatedBy;

	private Date updatedAt;
	
	private String userPcIp;
	
	private String sessionId;

	List<AnotherLanguageInfoDto> anotherLanguageInfos;
	
	

}
