/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.dto;

import java.util.Date;
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
public class GenericTherapyDto {
	
//	private IdentityDto genericTherapyId;
	
	private Long genericTherapyId;
	
//	private String genericTherapyNo;

	private String genericNo;
	
	private String genericName;

	private String therapyNo;
	
	private String therapyName;
	
	private int recordShowFlag ;

	private int activeStatusFlag;

	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;
	
	private Long updatedBy;
	
	private Date updatedAt;
	
	private String userPcIp;
	
	private String sessionId;

//	private List<GenericTherapyAnotherLanguageDto> anotherLanguageInfo;

}
