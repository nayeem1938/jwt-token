/**
 * 
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
@NoArgsConstructor
@AllArgsConstructor
public class TherapysDto {
	
	private IdentityDto therapyId;
	
	private String therapyNo;

	private String therapyName;
	
	private String therapyDescription;
	
	private String parentTherapyGroupNo;
	
	private String parentTherapyGroupName;
	
	private int recordShowFlag ;
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private String createdAt;
	
	private Long updatedBy;
	
	private String updatedAt;
	
	private String userDefineUpdatedBy;
	
	private String userPcIp;
	
	private String sessionId;
	
	List<AnotherLanguageInfoDto> anotherLanguageInfos;


}
