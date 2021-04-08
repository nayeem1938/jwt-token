/**
 * 
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
public class ProductTypeDto {

	private IdentityDto productTypeId;

	private String productTypeNo;

	private String productTypeName;

	private String productTypeDescription;

	private String parentProductTypeNo;
	
	private String parentProductTypeName;

	private String userDefineProductTypeNo;

	private int recordShowFlag;

	private int activeStatusFlag;

	private Long userDefineSerialNumber;

	private Long createdBy;

	private Date createdAt;

	private Long updatedBy;

	private Date updatedAt;
	
	private String userPcIp;
	
	private String sessionId;
	
	private List<AnotherLanguageInfoDto> anotherLanguageInfos;

}
